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
        request.setSessionId("a895364c-1700-46a6-8253-ae9674a8529a.1622014974");
        request.setMessageType(3);
        request.setContent("你好");
        request.setFileUrl("https://clink-resource-test.oss-cn-beijing.aliyuncs.com/chat/message/8000581/20210526/agent/8000581_20210526154216080_agent_f7ec5c4f-79ab-4dc9-a86e-416c7a6a2e5c.1622014798_2b896b1dcef7.txt?Expires=1622022136&OSSAccessKeyId=LTAI4G44RHvg2beXagryMPdK&Signature=xbNjHGDxrzSrUvzB86%2FnQLS804I%3D&response-content-disposition=attachment%3B%20filename%3D%E8%BF%94%E5%9B%9E%E7%A4%BA%E4%BE%8B.txt");
        ChatMessageToClientResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
