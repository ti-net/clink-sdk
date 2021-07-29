package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.request.chat.ChatVisitorCloseSessionRequest;
import com.tinet.clink.openapi.response.chat.ChatVisitorCloseSessionResponse;
import org.junit.Before;
import org.junit.Test;

public class ChatVisitorCloseSessionTest {
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
    public void testCloseSession() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ChatVisitorCloseSessionRequest request = new ChatVisitorCloseSessionRequest();
        request.setSessionId("f4cc8391-58e9-4c0e-a483-48c5e9330fb9.1623208279");
        ChatVisitorCloseSessionResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
