package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.request.chat.ChatClientLoginRequest;
import com.tinet.clink.openapi.request.chat.ChatClientWithdrawRequest;
import com.tinet.clink.openapi.response.ResponseModel;
import com.tinet.clink.openapi.response.chat.ChatClientLoginResponse;
import com.tinet.clink.openapi.response.chat.ChatClientWithdrawResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * 客服会话开始
 */
public class ChatClientWithdrawTest {
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
    public void testClientWithdraw() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ChatClientWithdrawRequest request = new ChatClientWithdrawRequest();
        request.setCno("061101");
        request.setSessionId("afbc3505-000f-498b-b806-dfbe911110b9.1623911645");
        request.setVisitorId("a4c45ef2-67f7-4ffc-b770-3c9ec0ce1417");
        request.setMessageId("27beb417-394d-45a1-9304-166f4cfbb8b7");
        ChatClientWithdrawResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
