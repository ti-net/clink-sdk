package com.tinet.clink.core.client;

import com.tinet.clink.core.auth.Credentials;

/**
 * @author houfc
 */
public class ClientConfiguration {
    private String host = "api-bj.clink.cn";

    private int port = 80;

    private String scheme = "https";

    private String accessKeyId = null;
    private String accessKeySecret = null;
    private String env = null;

    private int timeOffsetSeconds =0;

    /**
     * 连接超时时长（毫秒），默认值为3000，可以修改
     */
    private int connectTimeout = 3000;

    /**
     * 数据传输超时时长（毫秒），不可修改
     */
    private int SocketTimeout = 60000;

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

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public int getTimeOffsetSeconds() {
        return timeOffsetSeconds;
    }

    public void setTimeOffsetSeconds(int timeOffsetSeconds) {
        this.timeOffsetSeconds = timeOffsetSeconds;
    }

    /**
     * 获取连接超时时长（毫秒）
     *
     * @return 连接超时时长（毫秒）
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * 设置连接超时时长（毫秒），默认值为3000
     *
     * @param connectTimeout 连接超时时长（毫秒）
     */
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    /**
     * 获取数据传输超时时长（毫秒）
     *
     * @return 数据传输超时时长（毫秒）
     */
    public Integer getSocketTimeout() {
        return SocketTimeout;
    }

}
