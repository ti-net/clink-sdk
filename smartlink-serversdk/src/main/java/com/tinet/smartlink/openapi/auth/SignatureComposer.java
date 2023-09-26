package com.tinet.smartlink.openapi.auth;

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
        if (sortedParameterString.length() != 0 ) {
            sortedParameterString = new StringBuilder(sortedParameterString.substring(0, sortedParameterString.length() - 1));
        }

        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append(httpMethod)
                .append(domain)
                .append(uri)
                .append(sortedParameterString);

        return stringToSign.toString();
    }
}
