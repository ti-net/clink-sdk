package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.request.chat.ChatClientLoginRequest;
import com.tinet.clink.openapi.request.chat.ChatMessageToVisitorRequest;
import com.tinet.clink.openapi.response.chat.ChatClientLoginResponse;
import com.tinet.clink.openapi.response.chat.ChatMessageToVisitorResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * 客服会话开始
 */
public class ChatMessageToVisitorTest {
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
    public void testClientLogin() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ChatMessageToVisitorRequest request = new ChatMessageToVisitorRequest();
        request.setCno("061101");
        request.setSenderType(1);
        request.setSessionId("1823c4b9-330c-4a8e-96dc-6192902b0519.1624331768");
        request.setMessageType(1);
        request.setContent("123456");
        request.setFileUrl("https://clink-resource-test.oss-cn-beijing.aliyuncs.com/chat/message/8000581/20210531/agent/8000581_20210531183047271_agent_6160db08-fde3-4945-b4d3-5ff7699f6b77.1622456816_595210109bac.mp4?Expires=1622457259&OSSAccessKeyId=LTAI4G44RHvg2beXagryMPdK&Signature=r6AID6Qoon6IOVBczTg3VWYVneo%3D&response-content-disposition=attachment%3B%20filename%3DMP4%25E6%25A0%25BC%25E5%25BC%258F.mp4");
        ChatMessageToVisitorResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
