package com.tinet.smartlink.openapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.smartlink.openapi.auth.Signer;
import com.tinet.smartlink.openapi.exceptions.ClientException;
import com.tinet.smartlink.openapi.exceptions.ServerException;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.BaseResponse;
import com.tinet.smartlink.openapi.response.ErrorResponse;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>示例代码</p><br>
<pre>
        SmartlinkClientConfiguration configuration = new SmartlinkClientConfiguration();

        *******************************
        // 这些是必须设置的参数
        configuration.setAccessKeyId("");
        configuration.setAccessKeySecret("");
        configuration.setHost(new HttpHost(""))
        *******************************

        configuration.set...  设置其他参数

        SmartlinkClient smartLinkClient = new SmartlinkClient(configuration);

        SaveCdrRequest saveCdrRequest = new SaveCdrRequest();
        // 设置属性 saveCdrRequest.set...
        try {
            // 请求成功正常返回对应的 response
            SaveCdrResponse saveCdrResponse = smartLinkClient.getResponseModel(saveCdrRequest);
        } catch (ServerException e) {
            // 服务器错误，大概率是出 bug 了
            e.printStackTrace();
        } catch (ClientException e) {
            // 客户端错误，参数校验没通过？做了不该做的事？反正是你的事，再看看你写的代码
            e.printStackTrace();
        }
 </pre>
 详细文档：<a href="http://kb.tinetcloud.com/pages/viewpage.action?pageId=6293887">http://kb.tinetcloud.com/pages/viewpage.action?pageId=6293887</a>
 * @author houfc
 * @date 2018/11/26
 */
public class SmartlinkClient {

    private HttpClient httpClient;

    private HttpHost httpHost = null;

    private SmartlinkClientConfiguration configuration = null;

    private Signer signer = Signer.getSigner();

    private PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

    private HttpClientBuilder httpClientBuilder = null;

    private ObjectMapper objectMapper = new ObjectMapper();

    public SmartlinkClient(SmartlinkClientConfiguration configuration) {
        this.configuration = configuration;
        config(this.configuration);
        httpClient = getHttpClient();
        httpHost = new HttpHost(configuration.getHost());
    }

    public <T extends BaseResponse> HttpResponse execute(BaseRequest<T> request)
            throws ServerException, ClientException {
        return execute(request, httpHost);
    }

    public <T extends BaseResponse> HttpResponse execute(BaseRequest<T> request, HttpHost host)
            throws ClientException, ServerException {
        request.signRequest(signer, configuration.getCredentials(), host.getHostName(), request.uri());
        try {

            String url = host.getSchemeName()+"://" + host.getHostName() + ":" + host.getPort() + request.uri() + "?" + request.generateUri();
            StringEntity entity;
            switch (request.httpMethod().toString()) {
                case "GET":
                    HttpGet get = new HttpGet(URI.create(url));
                    setRequestConfig(get, request);
                    return getHttpClient().execute(get);
                case "POST":
                    HttpPost post = new HttpPost(URI.create(url));
                    entity = new StringEntity(objectMapper.writeValueAsString(request), ContentType.APPLICATION_JSON);
                    post.setEntity(entity);
                    setRequestConfig(post, request);
                    return getHttpClient().execute(post);
                case "DELETE":
                    HttpDelete delete = new HttpDelete(URI.create(url));
                    setRequestConfig(delete, request);
                    return getHttpClient().execute(delete);
                default:
                    break;
            }

            BasicHttpEntityEnclosingRequest httpRequest = new BasicHttpEntityEnclosingRequest(
                    request.httpMethod().toString(), request.uri() + "?" + request.generateUri());
            if (request.httpMethod().hasContent()) {
                entity = new StringEntity(objectMapper.writeValueAsString(request), ContentType.APPLICATION_JSON);
                httpRequest.setEntity(entity);
            }
            if (host != null) {
                httpHost = host;
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

    /**
     * 保证httpClient单例
     *
     * @return
     */
    private HttpClient getHttpClient() {
        if (httpClient == null) {
            //多线程下多个线程同时调用getHttpClient容易导致重复创建httpClient对象的问题,所以加上了同步锁
            synchronized (this) {
                if (httpClient == null) {
                    httpClientBuilder.setKeepAliveStrategy(
                            new SdkConnectionKeepAliveStrategy(this.configuration.getKeepAliveDurationMillis()));
                    if (configuration.getProxy() != null) {
                        // httpclient 设置代理 ，如果使用system property 会报错：
                        // org.apache.http.ProtocolException: The server failed to respond with a valid HTTP response
                        // https://cloud.tencent.com/developer/ask/35004
                        httpClientBuilder.setProxy(configuration.getProxy());
                    }
                    TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
                    SSLContext sslContext = null;
                    try {
                        sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
                    } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
                        e.printStackTrace();
                    }
                    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
                            NoopHostnameVerifier.INSTANCE);

                    Registry<ConnectionSocketFactory> socketFactoryRegistry =
                            RegistryBuilder.<ConnectionSocketFactory> create()
                                    .register("https", sslsf)
                                    .register("http", new PlainConnectionSocketFactory())
                                    .build();

                    BasicHttpClientConnectionManager connectionManager =
                            new BasicHttpClientConnectionManager(socketFactoryRegistry);

                    httpClientBuilder.setSSLSocketFactory(sslsf);
                    httpClientBuilder.setConnectionManager(connectionManager);

                    httpClient = httpClientBuilder.build();
                }
            }
        }
        return httpClient;
    }


    public <T extends BaseResponse> T getResponseModel (BaseRequest<T> request, String host)
            throws ServerException, ClientException {
        return getResponseModel(request, new HttpHost(host));
    }

    public <T extends BaseResponse> T getResponseModel (BaseRequest<T> request)
            throws ServerException, ClientException {
        return getResponseModel(request, this.httpHost);
    }

    public <T extends BaseResponse> T getResponseModel (BaseRequest<T> request, HttpHost host)
            throws ClientException, ServerException {
        HttpResponse response = execute(request, host);
        if (isSuccess(response)) {
            try {
                return readResponse(response, request.getResponseClass());
            } catch (IOException e) {
                throw new ClientException("ParseJsonError", "请求成功，解析返回结果失败，请确认SDK 版本是否正确", e);
            }
        } else {
            if (HttpStatus.SC_SERVICE_UNAVAILABLE == response.getStatusLine().getStatusCode()) {
                try {
                    // HttpClient有清理CLOSE_WAIT状态的机制，只有在读body操作后才会触发HttpClient Manager回收连接，
                    // 否则会被认为该连接一直在处理请求。因此在处理异常请求部分的代码中增加 EntityUtils.consume(response.getEntity()) 方法读body操作
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    throw new ServerException("ServiceUnavailable", "服务不可用，请稍后再试。" + e.getMessage());
                }
                throw new ServerException("ServiceUnavailable", "服务不可用，请稍后再试。");
            }
            ErrorResponse errorResponse = null;
            try {
                errorResponse = readResponse(response, ErrorResponse.class);
                if (HttpStatus.SC_INTERNAL_SERVER_ERROR >= response.getStatusLine().getStatusCode()) {
                    throw new ServerException(errorResponse.getError().getCode(), errorResponse.getError().getMessage(), errorResponse.getRequestId());
                } else {
                    throw new ClientException(errorResponse.getError().getCode(), errorResponse.getError().getMessage(), errorResponse.getRequestId());
                }
            } catch (IOException e) {
                throw new ServerException("ServiceError", "服务异常");
            }

        }
    }

    private boolean isSuccess(HttpResponse response) {
        StatusLine statusLine = response.getStatusLine();
        if (statusLine == null || statusLine.getStatusCode() >= 400) {
            return false;
        }
        return true;
    }

    private <T extends BaseResponse> T readResponse(HttpResponse response, Class<T> clazz) throws IOException {
        InputStream inputStream = response.getEntity().getContent();
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0 , length);
        }
        String str = result.toString();
        return objectMapper.readValue(str, clazz);

    }

    private void config(SmartlinkClientConfiguration configuration) {
        connectionManager.setMaxTotal(configuration.getMaxConnections());
        connectionManager.setDefaultMaxPerRoute(configuration.getMaxConnectionsPerRoute());
        httpClientBuilder = HttpClientBuilder.create().setConnectionManager(connectionManager);

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(configuration.getSocketTimeout())
                .setConnectTimeout(configuration.getConnectTimeout())
                .setConnectionRequestTimeout(configuration.getConnectionRequestTimeout())
                .build();
        httpClientBuilder.setDefaultRequestConfig(requestConfig);
    }

    /**
     * 对http请求进行设置
     *
     * @param httpRequestBase http请求
     */
    private void setRequestConfig(HttpRequestBase httpRequestBase, BaseRequest request) {
        Integer socketTimeout = request.getSocketTimeout();
        Integer connectionRequestTimeout = request.getConnectionRequestTimeout();
        Integer connectTimeout = request.getConnectTimeout();
        RequestConfig.Builder custom = RequestConfig.custom();
        if (socketTimeout!=null){
            custom.setSocketTimeout(socketTimeout);
        }
        if (connectionRequestTimeout!=null){
            custom.setConnectionRequestTimeout(connectionRequestTimeout);
        }
        if (connectTimeout!=null){
            custom.setConnectTimeout(connectTimeout);
        }
        httpRequestBase.setConfig(custom.build());
    }
}
