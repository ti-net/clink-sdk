package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.ws.AgentTokenRequest;
import com.tinet.clink.openapi.request.ws.AuthTokenRequest;
import com.tinet.clink.openapi.response.ws.AgentTokenResponse;
import com.tinet.clink.openapi.response.ws.AuthTokenResponse;
import org.junit.Test;

/**
 * @author libin
 * @date 2022-04-12 6:39 下午
 */
public class WsTokenTest extends AbstractTest {
    @Test
    public void getAuthToken() throws ServerException, ClientException, JsonProcessingException {
        AuthTokenRequest request = new AuthTokenRequest();
        request.setUsername("");
        //密码为md5加密之后的密文
        request.setPassword("");
        AuthTokenResponse response = client.getResponseModel(request);
        System.out.println(response.getAuthToken());
    }

    @Test
    public void getAgentToken() throws ServerException, ClientException, JsonProcessingException {
        AgentTokenRequest request = new AgentTokenRequest();
        request.setAuthToken("");
        request.setName("");
        request.setCno("");
        AgentTokenResponse response = client.getResponseModel(request);
        System.out.println(response.getAgentToken());
    }
}
