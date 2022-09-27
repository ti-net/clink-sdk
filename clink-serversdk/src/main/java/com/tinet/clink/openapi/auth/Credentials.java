package com.tinet.clink.openapi.auth;

/**
 * 提供 AccessKeyId 和 AccessKeySecret 用于 openapi 的身份验证
 * @author houfc
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class Credentials {

    private String accessKeyId;

    private String accessKeySecret;

    public Credentials(String accessKeyId, String accessKeySecret) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }
}
