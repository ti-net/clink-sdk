package com.tinet.clink.openapi.auth;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author houfc
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  SignatureComposer {

    private final static String SEPARATOR = "&";

    public String getStringToSign(String httpMethod, String domain, String uri, Map<String, String> queryParameters) {
        TreeMap<String, String> sortedParameters = new TreeMap<String, String>(queryParameters);
        StringBuilder sortedParameterString = new StringBuilder();
        for(String key : sortedParameters.keySet()) {
            sortedParameterString.append(key)
                    .append("=")
                    .append(sortedParameters.get(key))
                    .append("&");
        }

        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append(httpMethod)
                .append(domain)
                .append(uri)
                .append(sortedParameterString.substring(0, sortedParameterString.length() - 1));

        return stringToSign.toString();
    }
}
