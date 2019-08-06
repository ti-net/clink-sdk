package com.tinet.clink.openapi.auth;

/**
 * @author houfc
 */
public abstract class Signer {

    private final static Signer hmacSHA1Signer = new HmacSHA1Signer();

    public abstract String signString(String stringToSign, String accessKeySecret);

    public abstract String signString(String stringToSign, Credentials credentials);

    public abstract String getSignerName();

    public static Signer getSigner() {
        return hmacSHA1Signer;
    }

}
