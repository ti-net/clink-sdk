package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.livechat.request.ChatClientCloseSessionRequest;
import com.tinet.clink.livechat.response.ChatClientCloseSessionResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * 客服会话结束
 */
public class ChatClientCloseSessionTest {
    protected Client client = null;
    ClientConfiguration configuration = null;

    @Before
    public void init() {
        System.out.println("----------------------------->");
        configuration = new ClientConfiguration("706ff5f9bbb10286dcf7545262a7d702", "IO9Fpa392A3y54375Tvu");
        configuration.setScheme("http");
        configuration.setHost("api-bj-test5.clink.cn");

        client = new Client(configuration);
    }


    @Test
    public void testClientOpenSession() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ChatClientCloseSessionRequest request = new ChatClientCloseSessionRequest();
        request.setSessionId("f82aa089-b87d-4ffc-a1d3-653116c9b697.1623910930");
        ChatClientCloseSessionResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
