package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.request.chat.ChatQuitQueueRequest;
import com.tinet.clink.openapi.request.chat.ChatRobotTransferRequest;
import com.tinet.clink.openapi.response.chat.ChatQuitQueueResponse;
import com.tinet.clink.openapi.response.chat.ChatRobotTransferResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * 会话开始
 */
public class ChatRobotTransferTest {
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
    public void testRobotTransfer() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ChatRobotTransferRequest request = new ChatRobotTransferRequest();
        request.setSessionId("5f0dfecc-ceb4-4a35-ba7e-4bf5abe598df.1622014593");

        ChatRobotTransferResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
