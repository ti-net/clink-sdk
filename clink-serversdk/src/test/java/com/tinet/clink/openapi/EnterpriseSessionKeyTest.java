package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinet.clink.cc.request.ws.EnterpriseSessionKeyRequest;
import com.tinet.clink.cc.response.ws.EnterpriseSessionKeyResponse;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import org.junit.Test;

/**
 * @author libin
 * @date 2022-04-12 6:39 下午
 */
public class EnterpriseSessionKeyTest extends AbstractTest {

    @Test
    public void getEnterpriseSessionKey() throws ServerException, ClientException, JsonProcessingException {
        EnterpriseSessionKeyRequest request = new EnterpriseSessionKeyRequest();
        EnterpriseSessionKeyResponse response = client.getResponseModel(request);
        System.out.println(response.getSessionKey());
        System.out.println(response.getWsUrl());
    }

}
