package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.request.chat.ChatVisitorOpenSessionRequest;
import com.tinet.clink.openapi.response.chat.ChatVisitorOpenSessionResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * 会话开始
 */
public class ChatVisitorOpenSessionTest {
    protected Client client = null;
    ClientConfiguration configuration = null;

    @Before
    public void init() {
        System.out.println("----------------------------->");
        configuration = new ClientConfiguration("e67d46b40bcca7d888cf5182ca2ad976", "l508t7Z1L4fl30978m50");
        configuration.setScheme("https");
        configuration.setHost("api-bj.clink.cn");

        client = new Client(configuration);
    }


    @Test
    public void testOpenSession() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ChatVisitorOpenSessionRequest request = new ChatVisitorOpenSessionRequest();
        request.setAppId("086dd7a1-4daa-4295-a568-bb0854fa8123");
        ChatVisitorOpenSessionResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
        //{"requestId":"65818e12-b008-9883-a5b0-a121b2ab5912","sessionId":"84911939-51e5-4dcc-a5ef-5251c19475b9.1623209260","startTime":1623209260000}
    }
}
