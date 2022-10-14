package com.tinet.clink.openapi;

import com.tinet.clink.openapi.utils.RequestConstant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author houfc
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  AccessKeyAuth {

    /**
     * 默认编码
     */
    private static final String DEFAULT_ENCODING = "UTF-8";

    private TreeMap<String, String> sortedParams;

    public AccessKeyAuth(TreeMap<String, String> sortedParams) {
        this.sortedParams = sortedParams;
    }

    public String getStringToSign(String httpMethod, String domain, String uri) throws UnsupportedEncodingException {
        sortedParams.remove(RequestConstant.SIGNATURE);
        StringBuilder result = new StringBuilder();
        result.append(httpMethod).append(domain).append(uri).append("?");
        Set<String> keys = sortedParams.keySet();
        for (String key : keys) {
            result.append(percentEncode(key)).append("=").append(percentEncode(sortedParams.get(key))).append("&");
        }
        return result.substring(0, result.length() - 1);
    }

    public String percentEncode(String value) throws UnsupportedEncodingException {
        return value == null ? null : URLEncoder.encode(value, DEFAULT_ENCODING);
    }
}
