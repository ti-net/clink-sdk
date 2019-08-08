package com.tinet.clink.openapi;

import com.tinet.clink.openapi.auth.Credentials;

/**
 * @author houfc
 */
public class ClientConfiguration {
    private String host = "api-bj.clink.cn";

    private int port = 80;

    private String scheme = "https";

    private String accessKeyId = null;
    private String accessKeySecret = null;

    public ClientConfiguration(String accessKeyId, String accessKeySecret) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
    }

    public Credentials getCredentials() {
        return new Credentials(accessKeyId, accessKeySecret);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }
}
