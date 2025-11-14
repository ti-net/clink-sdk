package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.livechat.request.SessionInfoRequest;
import com.tinet.clink.livechat.response.SessionInfoResponse;
import org.junit.Test;

/**
 * 会话详情查看
 *
 * @author wangbin
 * @since 2024/12/10
 */
public class SessionInfoTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void sessionInfoTest() throws ServerException, ClientException, JsonProcessingException {
        SessionInfoRequest request = new SessionInfoRequest();
        request.setMainUniqueId("84b40d1c-3be1-437e-a560-3ca30bb3fde6.1733397784");
        ClientConfiguration clientConfiguration = new ClientConfiguration("58675b79b5582fe19ccb891ee1f8418a", "3l3q23wmX1WV4528x910");
        clientConfiguration.setScheme("https");
        clientConfiguration.setHost("api-bj-test0.clink.cn");
        Client client = new Client(clientConfiguration);
        SessionInfoResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getData()));
    }
}
