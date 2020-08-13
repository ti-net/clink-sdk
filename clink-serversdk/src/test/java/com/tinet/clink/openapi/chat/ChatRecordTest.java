package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.chat.ChatCommentRequest;
import com.tinet.clink.openapi.request.chat.ChatDetailRequest;
import com.tinet.clink.openapi.request.chat.ChatInvestigationRequest;
import com.tinet.clink.openapi.request.chat.ChatMessageRequest;
import com.tinet.clink.openapi.request.chat.ChatRecordRequest;
import com.tinet.clink.openapi.response.chat.ChatCommentResponse;
import com.tinet.clink.openapi.response.chat.ChatDetailResponse;
import com.tinet.clink.openapi.response.chat.ChatInvestigationResponse;
import com.tinet.clink.openapi.response.chat.ChatMessageResponse;
import com.tinet.clink.openapi.response.chat.ChatRecordResponse;
import org.junit.Test;

/**
 * @author Wangyl
 * @date 2020/7/29
 */
public class ChatRecordTest extends AbstractTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testChatRecord() throws ServerException, ClientException, JsonProcessingException {
        ChatRecordRequest request = new ChatRecordRequest();

        request.setLimit(10);
        request.setDate("20200813");
        //request.setScrollId("DnF1ZXJ5VGhlbkZldGNoAwAAAAABSWwoFkdfSmt5XzY1U2VHMFFaSTNkYjlPQ0EAAAAAAUlsJhZHX0preV82NVNlRzBRWkkzZGI5T0NBAAAAAAFJbCcWR19Ka3lfNjVTZUcwUVpJM2RiOU9DQQ==");

        ChatRecordResponse response = client.getResponseModel(request);
        System.out.println(response.getRecords().size());;
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testChatMessage() throws ServerException, ClientException, JsonProcessingException {
        ChatMessageRequest request = new ChatMessageRequest();

        request.setLimit(10);
        request.setDate("20200813");

        ChatMessageResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testChatDetail() throws ServerException, ClientException, JsonProcessingException {
        ChatDetailRequest request = new ChatDetailRequest();

        request.setLimit(10);
        request.setDate("20200813");
        //request.setScrollId("DnF1ZXJ5VGhlbkZldGNoAwAAAAABSWRpFkdfSmt5XzY1U2VHMFFaSTNkYjlPQ0EAAAAAAUlkahZHX0preV82NVNlRzBRWkkzZGI5T0NBAAAAAAFJZGsWR19Ka3lfNjVTZUcwUVpJM2RiOU9DQQ==");

        ChatDetailResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testChatComment() throws ServerException, ClientException, JsonProcessingException {
        ChatCommentRequest request = new ChatCommentRequest();

        request.setLimit(10);
        request.setDate("20200813");

        ChatCommentResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testChatInvestigation() throws ServerException, ClientException, JsonProcessingException {
        ChatInvestigationRequest request = new ChatInvestigationRequest();

        request.setLimit(10);
        request.setDate("20200813");

        ChatInvestigationResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }
}
