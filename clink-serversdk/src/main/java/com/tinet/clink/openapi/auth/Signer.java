package com.tinet.clink.openapi.auth;

/**
 * @author houfc
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public abstract class Signer {

    private final static Signer hmacSHA1Signer = new HmacSHA1Signer();

    public abstract String signString(String stringToSign, String accessKeySecret);

    public abstract String signString(String stringToSign, Credentials credentials);

    public abstract String getSignerName();

    public static Signer getSigner() {
        return hmacSHA1Signer;
    }

}
