package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.livechat.model.ChatMessageSyncModel;
import com.tinet.clink.livechat.request.ChatVisitorOpenSessionRequest;
import com.tinet.clink.livechat.response.ChatVisitorOpenSessionResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 会话开始
 */
public class ChatVisitorOpenSessionTest {
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
    public void testOpenSession() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ChatVisitorOpenSessionRequest request = new ChatVisitorOpenSessionRequest();
        request.setAppId("5d2e6ab7-3c79-4fe1-b83d-44cbb2e47a46");
        request.setCreateTime(new Date());

        ChatMessageSyncModel chatMessageSyncModel = new ChatMessageSyncModel();
        chatMessageSyncModel.setSenderType(4);
        chatMessageSyncModel.setSenderId("001");
        chatMessageSyncModel.setSenderName("啄木鸟机器人");
        chatMessageSyncModel.setMessageType(5);
        chatMessageSyncModel.setContent("sdk测试消息同步");
        chatMessageSyncModel.setCreateTime(new Date());
        List<ChatMessageSyncModel> messages = new ArrayList<>();
        messages.add(chatMessageSyncModel);

        request.setMessages(messages);
        ChatVisitorOpenSessionResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
        //{"requestId":"65818e12-b008-9883-a5b0-a121b2ab5912","sessionId":"84911939-51e5-4dcc-a5ef-5251c19475b9.1623209260","startTime":1623209260000}
    }
}
