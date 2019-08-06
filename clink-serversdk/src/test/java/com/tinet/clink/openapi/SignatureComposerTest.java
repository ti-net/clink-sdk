package com.tinet.clink.openapi;

import com.tinet.clink.openapi.auth.SignatureComposer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.SocketHandler;

/**
 * @author 侯法超
 */
public class SignatureComposerTest {

    private SignatureComposer composer = null;
    private String httpMethod = "GET";
    private String domain = "api-bj.clink.cn";
    private String uri = "/?";
    private Map<String, String> queryParameters;

    @Before
    public void init() {
        composer = new SignatureComposer();
        queryParameters = new HashMap<String, String>();
        queryParameters.put("param", "value");
        queryParameters.put("Action", "Demo");
    }

    @Test
    public void getStringToSignTest() {
        String stringToSign = composer.getStringToSign(httpMethod, domain, uri, queryParameters);
        Assert.assertEquals("GETapi-bj.clink.cn/?Action=Demo&param=value", stringToSign);
    }
}
