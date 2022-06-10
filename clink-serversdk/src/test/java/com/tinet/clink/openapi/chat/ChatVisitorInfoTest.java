package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.request.chat.ChatVisitorInfoRequest;
import com.tinet.clink.openapi.response.chat.ChatVisitorInfoResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * @author midong
 * @date 2022.06.08
 */
public class ChatVisitorInfoTest extends AbstractTest {

    protected Client client = null;
    ClientConfiguration configuration = null;
    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init() {
        System.out.println("----------------------------->");
        configuration = new ClientConfiguration("d8a0213a66aa9ff4fab60aa5ea99a40e", "70f8a39fcc822d2e52088614cf7aaa51b2d28d3d2ee8f7096eecf4dc6e71c970");
        configuration.setScheme("http");
        configuration.setHost("api-bj-test0.clink.cn");
        client = new Client(configuration);
        System.out.println("client--->>>>{}");
    }

    @Test
    public void testOpenSession() throws Exception {
        ChatVisitorInfoRequest request = new ChatVisitorInfoRequest();
        request.setAccessId("350cf26b-2a83-4c95-b214-85ad6a22bad4");
        request.setVisitorId("0b0bd5dc-490c-450a-baaf-44d8de01a978");
        ChatVisitorInfoResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
        System.out.println(response.getUnreadCount());
    }
}
