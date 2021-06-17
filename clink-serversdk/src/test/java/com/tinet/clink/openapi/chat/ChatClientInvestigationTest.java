package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.request.chat.ChatClientCloseSessionRequest;
import com.tinet.clink.openapi.response.chat.ChatClientCloseSessionResponse;
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
        ChatClientCloseSessionRequest request = new ChatClientCloseSessionRequest();
        request.setSessionId("str");
        ChatClientCloseSessionResponse responseModel = client.getResponseModel(request);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
