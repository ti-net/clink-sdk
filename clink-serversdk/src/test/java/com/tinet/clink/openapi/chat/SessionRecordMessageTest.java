package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.livechat.request.SessionMessageRequest;
import com.tinet.clink.livechat.response.SessionMessageResponse;
import org.junit.Test;

/**
 * @author wangbin
 * @since 2024/12/10
 */
public class SessionRecordMessageTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void sessionRecordMessageTest() throws ServerException, ClientException, JsonProcessingException {
        SessionMessageRequest request = new SessionMessageRequest();
        request.setMainUniqueId("84b40d1c-3be1-437e-a560-3ca30bb3fde6.1733397784");
        ClientConfiguration clientConfiguration = new ClientConfiguration("58675b79b5582fe19ccb891ee1f8418a", "3l3q23wmX1WV4528x910");
        clientConfiguration.setScheme("https");
        clientConfiguration.setHost("api-bj-test0.clink.cn");
        Client client = new Client(clientConfiguration);
        SessionMessageResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getData()));
    }
}
