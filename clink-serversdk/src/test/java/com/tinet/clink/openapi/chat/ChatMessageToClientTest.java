package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.request.chat.ChatMessageToClientRequest;
import com.tinet.clink.openapi.response.chat.ChatMessageToClientResponse;
import org.junit.Before;
import org.junit.Test;

public class ChatMessageToClientTest {
    protected Client client = null;
    ClientConfiguration configuration = null;

    @Before
    public void init() {

        configuration = new ClientConfiguration("706ff5f9bbb10286dcf7545262a7d702", "IO9Fpa392A3y54375Tvu");
        configuration.setScheme("http");
        configuration.setHost("api-bj-test3.clink.cn");

        client = new Client(configuration);
    }


    @Test
    public void testMessageToClient() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        ChatMessageToClientRequest request = new ChatMessageToClientRequest();
        request.setSessionId("5728526c-3088-4f9b-936a-d6f6a10f7fad.1621757501");
        request.setMessageType(1);
        request.setContent("你好");
        ChatMessageToClientResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
