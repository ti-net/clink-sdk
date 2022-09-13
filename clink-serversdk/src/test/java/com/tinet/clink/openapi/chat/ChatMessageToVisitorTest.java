package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.livechat.request.ChatMessageToVisitorRequest;
import com.tinet.clink.livechat.response.ChatMessageToVisitorResponse;
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
        configuration = new ClientConfiguration("1ad36475b5fb78c23f033250cb19a0a5", "li8FM094H5qj4O0SEdu8");
        configuration.setScheme("http");
        configuration.setHost("api-bj-test4.clink.cn");

        client = new Client(configuration);
    }


    @Test
    public void testClientLogin() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ChatMessageToVisitorRequest request = new ChatMessageToVisitorRequest();
        request.setCno("8886");
        request.setSenderType(1);
        request.setSessionId("60ab4ae7-a633-40f5-8bde-69f5b779a7b7.1637827908");
        request.setMessageType(1);
        request.setContent("123456");
        request.setFileUrl("https://clink-resource-test.oss-cn-beijing.aliyuncs.com/chat/message/8000581/20210531/agent/8000581_20210531183047271_agent_6160db08-fde3-4945-b4d3-5ff7699f6b77.1622456816_595210109bac.mp4?Expires=1622457259&OSSAccessKeyId=LTAI4G44RHvg2beXagryMPdK&Signature=r6AID6Qoon6IOVBczTg3VWYVneo%3D&response-content-disposition=attachment%3B%20filename%3DMP4%25E6%25A0%25BC%25E5%25BC%258F.mp4");
        ChatMessageToVisitorResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }
}
