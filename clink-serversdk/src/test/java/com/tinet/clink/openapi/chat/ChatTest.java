package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.chat.OpenRequest;
import com.tinet.clink.openapi.response.chat.OpenResponse;
import org.junit.Test;

/**
 * @author wangzb
 * @date 2020/8/9
 */
public class ChatTest extends AbstractTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testOpen() throws JsonProcessingException, ServerException, ClientException {
        OpenRequest request = new OpenRequest();
        request.setAppId("123");
        request.setVisitorId("123");
        request.setVisitorName("123");
        OpenResponse response = client.getResponseModel(request);
        System.out.println(objectMapper.writeValueAsString(response));
    }
}
