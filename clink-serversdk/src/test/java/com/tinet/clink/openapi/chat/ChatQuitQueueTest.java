package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.model.ChatInvestigationOptionModel;
import com.tinet.clink.openapi.request.chat.ChatQuitQueueRequest;
import com.tinet.clink.openapi.request.chat.ChatSubmitInvestigationRequest;
import com.tinet.clink.openapi.response.chat.ChatQuitQueueResponse;
import com.tinet.clink.openapi.response.chat.ChatSubmitInvestigationResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * 会话开始
 */
public class ChatQuitQueueTest {
    protected Client client = null;
    ClientConfiguration configuration = null;

    @Before
    public void init() {
        System.out.println("----------------------------->");
        configuration = new ClientConfiguration("706ff5f9bbb10286dcf7545262a7d702", "IO9Fpa392A3y54375Tvu");
        configuration.setScheme("http");
        configuration.setHost("api-bj-test3.clink.cn");

        client = new Client(configuration);
    }


    @Test
    public void testQuitQueue() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ChatQuitQueueRequest request = new ChatQuitQueueRequest();
        request.setSessionId("4d8ecc81-1384-40fb-a430-8d8fa0133e4b.1622014405");

        ChatQuitQueueResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
