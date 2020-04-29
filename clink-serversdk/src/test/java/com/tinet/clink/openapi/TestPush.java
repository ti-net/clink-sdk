package com.tinet.clink.openapi;

import com.tinet.clink.openapi.auth.HmacSHA1Signer;
import com.tinet.clink.openapi.auth.SignatureComposer;
import com.tinet.clink.openapi.utils.AesUtil;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class TestPush extends AbstractTest {

    @Test
    public void push() throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException,
            IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException {

        Map<String, String> params = new HashMap<String, String>();

        params.put("request_no", AesUtil.encrypt("20200428", "access-key-sercret"));
        params.put("dealer_id", AesUtil.encrypt("36131", "access-key-sercret"));
        params.put("flag", AesUtil.encrypt("y", "access-key-sercret"));


        SignatureComposer signatureComposer = new SignatureComposer();
        String signText = signatureComposer.getStringToSign("", "", "", params);

        HmacSHA1Signer hmacSHA1Signer = new HmacSHA1Signer();
        String sign = hmacSHA1Signer.signString(signText, "access-key-sercret");

        params.put("sign", sign);

    }
}
