package com.tinet.clink.openapi.aikb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.aikb.model.ChatSessionModel;
import com.tinet.clink.aikb.model.FaqAnswerModel;
import com.tinet.clink.aikb.model.MessageModel;
import com.tinet.clink.aikb.request.ChatSessionPushRequest;
import com.tinet.clink.aikb.request.FaqCreateRequest;
import com.tinet.clink.aikb.response.ChatSessionPushResponse;
import com.tinet.clink.aikb.response.FaqCreateResponse;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author houwd
 * @since 2024/06/25
 */
public class ChatSessionPushTest extends AbstractTest {

    @Test
    public void chatSessionPush() {

        ClientConfiguration configuration = new ClientConfiguration(
                "e8a61c46f20809457831f281f973feef",           // AccessKeyId
                "49z710c29PMZhi67j203");     // AccessKeySecret
        configuration.setHost("clink2-openapi-dev.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        ChatSessionPushRequest request = new ChatSessionPushRequest();
        List<ChatSessionModel> sessions = new ArrayList<>();
        ChatSessionModel session = new ChatSessionModel();
        session.setUniqueId("session5");
        session.setStartTime(LocalDateTime.now());
        session.setEndTime(LocalDateTime.now());

        List<MessageModel> messageModels = new ArrayList<>();
        MessageModel messageModel = new MessageModel();
        messageModel.setText("content");
        messageModel.setSide(1);
        messageModels.add(messageModel);

        session.setMessages(messageModels);
        sessions.add(session);

        request.setSessions(sessions);

        try {
            ChatSessionPushResponse responseModel = client.getResponseModel(request);

            ObjectMapper objectMapper = new ObjectMapper();

            System.out.println(objectMapper.writeValueAsString(responseModel));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
