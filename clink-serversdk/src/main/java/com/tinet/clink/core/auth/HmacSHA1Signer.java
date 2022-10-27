package com.tinet.clink.core.auth;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author houfc
 */
public class HmacSHA1Signer extends Signer {

    private static final String ALGORITHM_NAME = "HmacSHA1";
    public static final String ENCODING = "UTF-8";

    @Override
    public String signString(String stringToSign, String accessKeySecret) {
        try {
            Mac mac = Mac.getInstance(ALGORITHM_NAME);
            mac.init(new SecretKeySpec(accessKeySecret.getBytes(ENCODING), ALGORITHM_NAME));
            byte[] signData = mac.doFinal(stringToSign.getBytes(ENCODING));
            return DatatypeConverter.printBase64Binary(signData);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e.toString());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e.toString());
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e.toString());
        }
    }

    @Override
    public String signString(String stringToSign, Credentials credentials) {
        return signString(stringToSign, credentials.getAccessKeySecret());
    }

    @Override
    public String getSignerName() {
        return ALGORITHM_NAME;
    }

}
