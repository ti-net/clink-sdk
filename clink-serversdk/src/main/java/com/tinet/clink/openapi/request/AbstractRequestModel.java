package com.tinet.clink.openapi.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinet.clink.openapi.auth.Credentials;
import com.tinet.clink.openapi.auth.SignatureComposer;
import com.tinet.clink.openapi.auth.Signer;
import com.tinet.clink.openapi.response.ResponseModel;
import com.tinet.clink.openapi.utils.HttpMethodType;
import com.tinet.clink.openapi.utils.RequestConstant;
import org.apache.http.client.utils.URIBuilder;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author houfc
 */
public abstract class AbstractRequestModel<T extends ResponseModel> {

    private int expires = 5;

    private String path;

    private HttpMethodType httpMethod = HttpMethodType.GET;

    private SignatureComposer composer = null;

    private final Map<String, String> queryParameters = new TreeMap<String, String>();

    /**
     * multipart 添加附件列表
     */
    protected Map<String, List<File>> fileMap = null;

    /**
     * multipart 添加文本参数
     */
    protected Object model = null;

    public AbstractRequestModel(String path) {
        this.composer = new SignatureComposer();
        this.path = path;
    }

    public AbstractRequestModel(String path, HttpMethodType httpMethod) {
        this.composer = new SignatureComposer();
        this.path = path;
        this.httpMethod = httpMethod;
    }

    public void signRequest(Signer signer, Credentials credentials, String domain) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        putQueryParameter(RequestConstant.ACCESS_KEY_ID, credentials.getAccessKeyId());
        putQueryParameter(RequestConstant.EXPIRES, expires);
        putQueryParameter(RequestConstant.TIMESTAMP, sdf.format(new Date()));

        String stringToSign = composer.getStringToSign(httpMethod.toString(), domain, "/" + path + "?",
                queryParameters);
        String signature = signer.signString(stringToSign, credentials);
        putQueryParameter(RequestConstant.SIGNATURE, signature);
    }

    protected void putQueryParameter(String name, Object value) {
        try {
            String encodedKey = URLEncoder.encode(name, "UTF-8");
            String encodedValue = URLEncoder.encode(String.valueOf(value), "UTF-8");
            queryParameters.put(encodedKey, encodedValue);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void expires(int expires) {
        this.expires = expires;
    }

    public int expires() {
        return expires;
    }

    /**
     * 配置请求体参数
     *
     * @param name  参数名
     * @param value 参数值
     */
    protected void putBodyParameter(String name, Object value) {
    }

    /**
     * 获取响应对象
     *
     * @return 响应对象
     */
    @JsonIgnore
    public abstract Class<T> getResponseClass();

    @JsonIgnore
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;

    }

    public String generateUri() throws URISyntaxException {
        URIBuilder builder = new URIBuilder();
        for (String key : queryParameters.keySet()) {
            builder.setParameter(key, queryParameters.get(key));
        }
        return builder.build().getQuery();
    }

    public HttpMethodType httpMethod() {
        return httpMethod;
    }

    @JsonIgnore
    public boolean isMultipartFormData() {
        return false;
    }

    @JsonIgnore
    public Map<String, List<File>> getFileMap() {
        return fileMap;
    }

    @JsonIgnore
    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

    public void setFileMap(Map<String, List<File>> fileMap) {
        this.fileMap = fileMap;
    }
}
