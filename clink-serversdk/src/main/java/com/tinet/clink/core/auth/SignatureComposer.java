package com.tinet.clink.core.auth;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author houfc
 */
public class SignatureComposer {

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
