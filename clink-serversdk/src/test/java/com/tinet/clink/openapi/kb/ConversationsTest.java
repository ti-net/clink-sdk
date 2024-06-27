package com.tinet.clink.openapi.kb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.aikb.request.*;
import com.tinet.clink.aikb.response.*;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import org.junit.Test;

/**
 * @author zhangpc
 * @since 2024/06/05
 */
public class ConversationsTest extends AbstractTest  {



    @Test
    public void chatListConversations() {

        ClientConfiguration configuration = new ClientConfiguration(
                "ak",           // AccessKeyId
                "sk");     // AccessKeySecret
        configuration.setHost("host");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        ChatListConversationsRequest request = new ChatListConversationsRequest();
        request.setUserId(2);
        request.setOffset(0);
        request.setLimit(10);

        try {
            ChatListConversationsResponse responseModel = client.getResponseModel(request);

            ObjectMapper objectMapper = new ObjectMapper();

            System.out.println(objectMapper.writeValueAsString(responseModel));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void chatConversationRequest() {

        ClientConfiguration configuration = new ClientConfiguration(
                "ak",           // AccessKeyId
                "sk");     // AccessKeySecret
        configuration.setHost("host");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        ChatConversationRequest request = new ChatConversationRequest();
        request.setContent("1");
        request.setUserId(2);

        try {
            ChatConversationResponse responseModel = client.getResponseModel(request);

            ObjectMapper objectMapper = new ObjectMapper();

            System.out.println(objectMapper.writeValueAsString(responseModel));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void chatDescribeConversation() {

        ClientConfiguration configuration = new ClientConfiguration(
                "ak",           // AccessKeyId
                "sk");     // AccessKeySecret
        configuration.setHost("host");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        ChatDescribeConversationRequest request = new ChatDescribeConversationRequest();
        request.setConversationId("2dc627f2472c4bc1b804fedda0c761ea");
        request.setOffset(0);
        request.setLimit(10);

        try {
            ChatDescribeConversationResponse responseModel = client.getResponseModel(request);

            ObjectMapper objectMapper = new ObjectMapper();

            System.out.println(objectMapper.writeValueAsString(responseModel));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void chatUpdateConversationRequest() {

        ClientConfiguration configuration = new ClientConfiguration(
                "ak",           // AccessKeyId
                "sk");     // AccessKeySecret
        configuration.setHost("host");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        ChatUpdateConversationRequest request = new ChatUpdateConversationRequest();
        request.setConversationId("0871c3b15b2e42ccae666ffdb9c07fb3");
        request.setTitle("zhangpc2");

        try {
            ChatUpdateConversationResponse responseModel = client.getResponseModel(request);

            ObjectMapper objectMapper = new ObjectMapper();

            System.out.println(objectMapper.writeValueAsString(responseModel));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void chatDeleteConversationRequest() {

        ClientConfiguration configuration = new ClientConfiguration(
                "ak",           // AccessKeyId
                "sk");     // AccessKeySecret
        configuration.setHost("host");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        ChatDeleteConversationRequest request = new ChatDeleteConversationRequest();
        request.setConversationId("2dc627f2472c4bc1b804fedda0c761ea");

        try {
            ChatDeleteConversationResponse responseModel = client.getResponseModel(request);

            ObjectMapper objectMapper = new ObjectMapper();

            System.out.println(objectMapper.writeValueAsString(responseModel));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
