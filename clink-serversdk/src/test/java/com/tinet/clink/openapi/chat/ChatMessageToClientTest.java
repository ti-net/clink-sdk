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

        configuration = new ClientConfiguration("e67d46b40bcca7d888cf5182ca2ad976", "l508t7Z1L4fl30978m50");
        configuration.setScheme("https");
        configuration.setHost("api-bj.clink.cn");

        client = new Client(configuration);
    }




    @Test
    public void testMessageToClient() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        ChatMessageToClientRequest request = new ChatMessageToClientRequest();
        request.setSessionId("8d5da47d-0e1e-40ed-8304-61e3d48d2a65.1623209143");
        request.setMessageType(1);
        request.setContent("你好");
        request.setFileUrl("");
        ChatMessageToClientResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
