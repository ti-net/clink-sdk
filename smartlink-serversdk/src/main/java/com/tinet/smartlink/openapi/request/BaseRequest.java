package com.tinet.smartlink.openapi.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.auth.Credentials;
import com.tinet.smartlink.openapi.auth.SignatureComposer;
import com.tinet.smartlink.openapi.auth.Signer;
import com.tinet.smartlink.openapi.response.BaseResponse;
import com.tinet.smartlink.openapi.utils.RequestConstant;
import org.apache.http.client.utils.URIBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * @param <T> 一组请求中对应的 Response ,需要实现 BaseResponse
 * @author houfc
 * @date 2018/11/26
 */
public abstract class BaseRequest<T extends BaseResponse> {

    private int expires = 5;

    private HttpMethodType httpMethod;

    private SignatureComposer composer = new SignatureComposer();

    private final Map<String, String> queryParameters = new TreeMap<>();

    private final Map<String, String> bodyParameters = new TreeMap<>();

    private String uri = "/";

    /**
     * 超时时间
     */
    private Integer connectTimeout;
    private Integer connectionRequestTimeout;
    private Integer socketTimeout;

    /**
     * @param uri        请求的 URI
     * @param httpMethod http 请求方法
     */
    protected BaseRequest(String uri, HttpMethodType httpMethod) {
        this(uri, httpMethod, null);
    }

    /**
     * 添加version 时 Controller的 GetMapping 注解需要加上 params 参数 其值为 Version={SDK中version 值}
     * <pre>
     *     GetMapping(value = "/check", params = "Version=2019-06-10")
     * </pre>
     *
     * @param uri        请求的 URI
     * @param httpMethod http 请求方法
     * @param version    该请求所使用的版本号，建议以上线日期为准备，例如: 2019-06-10
     */
    protected BaseRequest(String uri, HttpMethodType httpMethod, String version) {
        this.httpMethod = httpMethod;
        uri(uri);
        if (version != null) {
            putQueryParameter("Version", version);
        }
    }

    private void uri(String uri) {
        this.uri = uri;
    }

    public void signRequest(Signer signer, Credentials credentials, String domain, String uri) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        putQueryParameter(RequestConstant.ACCESS_KEY_ID, credentials.getAccessKeyId());
        putQueryParameter(RequestConstant.EXPIRES, expires);
        putQueryParameter(RequestConstant.TIMESTAMP, sdf.format(new Date()));

        String stringToSign = composer.getStringToSign(httpMethod.toString(), domain, uri + "?", queryParameters);
        String signature = signer.signString(stringToSign, credentials);
        putQueryParameter(RequestConstant.SIGNATURE, signature);
    }

    public String generateUri() throws URISyntaxException {
        URIBuilder builder = new URIBuilder();
        for (String key : queryParameters.keySet()) {
            builder.setParameter(key, queryParameters.get(key));
        }
        return builder.build().getQuery();
    }

    public int expires() {
        return expires;
    }

    public void expires(int expires) {
        this.expires = expires;
    }

    public HttpMethodType httpMethod() {
        return httpMethod;
    }

    public String uri() {
        return uri;
    }

    protected void putQueryParameter(String key, Object value) {
        try {
            String encodedKey = URLEncoder.encode(key, "UTF-8");
            String encodedValue = URLEncoder.encode(String.valueOf(value), "UTF-8");
            queryParameters.put(encodedKey, encodedValue);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    protected void removeQueryParameter(String key) {
        queryParameters.remove(key);
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(Integer connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    /**
     * @return 一组请求中对应的 Response ,需要实现 BaseResponse
     */
    @JsonIgnore
    public abstract Class<T> getResponseClass();

    protected void putBodyParameter(String key, Object value) {
        if (key == null || value == null) {
            return;
        }
        bodyParameters.put(key, String.valueOf(value));
    }
}
