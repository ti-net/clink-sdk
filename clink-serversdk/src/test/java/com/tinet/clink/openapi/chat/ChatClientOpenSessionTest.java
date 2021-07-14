package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.request.chat.ChatClientOpenSessionRequest;
import com.tinet.clink.openapi.request.chat.ChatVisitorOpenSessionRequest;
import com.tinet.clink.openapi.response.chat.ChatClientOpenSessionResponse;
import com.tinet.clink.openapi.response.chat.ChatVisitorOpenSessionResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * 客服会话开始
 */
public class ChatClientOpenSessionTest {
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
        ChatClientOpenSessionRequest request = new ChatClientOpenSessionRequest();
        request.setCno("061101");
        request.setSessionId("665c04b7-5846-418a-b12f-896c30751c08.1623906024");
        request.setStartTime(1623906024000L);
        ChatClientOpenSessionResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
