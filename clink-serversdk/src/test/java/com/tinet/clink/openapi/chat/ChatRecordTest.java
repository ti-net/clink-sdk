package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.livechat.request.*;
import com.tinet.clink.livechat.response.*;
import com.tinet.clink.openapi.AbstractTest;

import com.tinet.clink.core.exceptions.ServerException;

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
        request.setDate("20210928");
        //request.setScrollId("DnF1ZXJ5VGhlbkZldGNoAwAAAAABSWwoFkdfSmt5XzY1U2VHMFFaSTNkYjlPQ0EAAAAAAUlsJhZHX0preV82NVNlRzBRWkkzZGI5T0NBAAAAAAFJbCcWR19Ka3lfNjVTZUcwUVpJM2RiOU9DQQ==");

        ChatRecordResponse response = client.getResponseModel(request);
        System.out.println(response.getRecords().size());;
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testChatMessage() throws ServerException, ClientException, JsonProcessingException {
        ChatMessageRequest request = new ChatMessageRequest();

        request.setLimit(100);
        request.setDate("20210928");

        ChatMessageResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testChatMessageDetail() throws ServerException, ClientException, JsonProcessingException {
        ListChatMessageRequest request = new ListChatMessageRequest();

        request.setLimit(100);
        request.setDate("20211019");
        request.setMainUniqueId("677586f3-c8a7-4e08-bfd4-8ba9e0be152c.1634632141");

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
        request.setDate("20220714");
        request.setMainUniqueId("26bbaa41-180e-4fc7-aafa-a165506b28e7.1657786082");
        ChatInvestigationResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }
}
