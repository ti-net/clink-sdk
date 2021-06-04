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

        configuration = new ClientConfiguration("706ff5f9bbb10286dcf7545262a7d702", "IO9Fpa392A3y54375Tvu");
        configuration.setScheme("http");
        configuration.setHost("api-bj-test3.clink.cn");

        client = new Client(configuration);
    }

    @Test
    public void testCloseSession() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ChatVisitorCloseSessionRequest request = new ChatVisitorCloseSessionRequest();
        request.setSessionId("1592fa50-f46f-4c98-a07a-f9f18cd99b8f.1621945232");
        ChatVisitorCloseSessionResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
