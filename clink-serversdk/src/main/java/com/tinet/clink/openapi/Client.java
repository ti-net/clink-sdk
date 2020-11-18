package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.auth.Signer;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.model.ErrorCode;
import com.tinet.clink.openapi.model.OpenapiError;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ResponseModel;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.FormBodyPartBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.protocol.HttpContext;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author houfc
 */
public class Client {

    private final ObjectMapper mapper = new ObjectMapper();

    private HttpClient httpClient = null;

    private HttpHost httpHost = null;

    private int maxRetryNumber = 3;

    private ClientConfiguration configuration = null;

    private Signer signer = Signer.getSigner();

    private static final ObjectMapper CONTENT_OBJECT_MAPPER = new ObjectMapper();

    static {
        CONTENT_OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Client(ClientConfiguration configuration) {
        this.configuration = configuration;
        httpClient = HttpClientBuilder.create()
                //增加空闲线程回收机制
                .evictIdleConnections(5, TimeUnit.SECONDS)
                //增加失败请求重试机制
                .setRetryHandler(new HttpRequestRetryHandler() {
                    @Override
                    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {

                        if (executionCount > maxRetryNumber) {
                            return false;
                        }

                        //增加重试机制，处理服务器主动丢弃连接后，新的请求从HttpClient连接池拿到无效的后无法获取响应的问题
                        if (exception instanceof NoHttpResponseException) {
                            return true;
                        }
                        return false;
                    }
                })
                .build();

        if (configuration.getPort() == 80) {
            httpHost = new HttpHost(this.configuration.getHost(), -1, this.configuration.getScheme());
        } else {
            httpHost = new HttpHost(this.configuration.getHost(), this.configuration.getPort(),
                    this.configuration.getScheme());
        }
    }

    public <T extends ResponseModel> HttpResponse doAction(AbstractRequestModel<T> request) throws ClientException {
        try {
            request.signRequest(signer, configuration.getCredentials(), configuration.getHost());
            String method = request.httpMethod().toString();
            String uri = "/" + request.getPath() + "/?" + request.generateUri();
            BasicHttpEntityEnclosingRequest httpRequest = new BasicHttpEntityEnclosingRequest(method, uri);

            if (request.httpMethod().hasContent()) {
                if (request.isMultipartFormData()) {
                    MultipartEntityBuilder builder = getMultipartEntityBuilder(request);
                    httpRequest.setEntity(builder.build());
                } else {
                    StringEntity entity = new StringEntity(mapper.writeValueAsString(request),
                            ContentType.APPLICATION_JSON);
                    httpRequest.setEntity(entity);
                }
            }

            return httpClient.execute(httpHost, httpRequest);
        } catch (URISyntaxException e) {
            throw new ClientException("SDK", "URI 错误", e);
        } catch (ClientProtocolException e) {
            throw new ClientException("SDK", "SDK 协议错误", e);
        } catch (IOException e) {
            throw new ClientException("SDK", "服务器连接失败", e);
        }
    }

    private <T extends ResponseModel> MultipartEntityBuilder getMultipartEntityBuilder(AbstractRequestModel<T> request) throws JsonProcessingException {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        // 设置字符编码
        builder.setCharset(StandardCharsets.UTF_8);

        // multipart data 普通字符的处理
        Object model = request.getModel();
        if (Objects.nonNull(model)) {
            String modelStr;
            if (model instanceof String) {
                modelStr = (String) model;
            } else {
                modelStr = mapper.writeValueAsString(model);
            }
            builder.addTextBody("model", modelStr, ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), Consts.UTF_8));
        }

        // multipart data附件的处理
        Map<String, List<File>> fileMap = request.getFileMap();
        if (Objects.isNull(fileMap)) {
            return builder;
        }
        for (Map.Entry<String, List<File>> entry : fileMap.entrySet()) {
            if (Objects.isNull(entry.getValue()) || entry.getValue().size() == 0) {
                continue;
            }
            for (File file : entry.getValue()) {
                if (Objects.isNull(file) || file.length() == 0) {
                    continue;
                }
                builder.addPart(FormBodyPartBuilder.create(entry.getKey(), new FileBody(file)).build());
            }
        }
        return builder;
    }

    public <T extends ResponseModel> T getResponseModel(AbstractRequestModel<T> request) throws ClientException,
            ServerException {
        HttpResponse response = doAction(request);
        if (isSuccess(response)) {
            return readResponse(response, request.getResponseClass());
        } else {
            OpenapiError openapiError = readError(response);
            ErrorCode errorCode = openapiError.getError();
            if (500 <= openapiError.getHttpStatus()) {
                throw new ServerException(openapiError.getRequestId(), errorCode.getCode(), errorCode.getMessage());
            } else {
                throw new ClientException(openapiError.getRequestId(), errorCode.getCode(), errorCode.getMessage());
            }
        }
    }

    private boolean isSuccess(HttpResponse response) {
        StatusLine statusLine = response.getStatusLine();
        return statusLine != null && statusLine.getStatusCode() < 400;
    }

    private OpenapiError readError(HttpResponse response) throws ClientException, ServerException {
        OpenapiError error = null;
        try {
            error = getHttpContentObject(response, OpenapiError.class);
        } catch (IOException e) {
            if (response.getStatusLine().getStatusCode() == 503) {
                throw new ServerException("ServiceUnavailable", "服务暂时不可用，请稍后再试");
            } else {
                throw new ServerException("InternalError", "服务返回错误码异常");
            }
        }
        error.setHttpStatus(response.getStatusLine().getStatusCode());
        return error;
    }

    private <T extends ResponseModel> T readResponse(HttpResponse response, Class<T> clazz) throws ClientException {
        try {
            return getHttpContentObject(response, clazz);
        } catch (IOException e) {
            throw new ClientException("SDK", "操作执行成功，但SDK读取返回结果失败", e);
        }
    }

    private String getHttpContentString(HttpResponse response) throws IOException {
        InputStream in = response.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader.readLine();
    }

    private <T> T getHttpContentObject(HttpResponse response, Class<T> clazz) throws IOException {
        return CONTENT_OBJECT_MAPPER.readValue(response.getEntity().getContent(), clazz);
    }

}
