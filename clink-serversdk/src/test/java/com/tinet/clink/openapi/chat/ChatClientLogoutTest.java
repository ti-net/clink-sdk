package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.livechat.request.ChatClientLogoutRequest;
import com.tinet.clink.livechat.response.ChatClientLogoutResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * 客服会话开始
 */
public class ChatClientLogoutTest {
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
    public void testClientLogout() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ChatClientLogoutRequest request = new ChatClientLogoutRequest();
        request.setCno("061101");
        ChatClientLogoutResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
