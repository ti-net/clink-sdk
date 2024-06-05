package com.tinet.clink.openapi.kb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.kb.request.*;
import com.tinet.clink.kb.response.*;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.request.ListTicketRequest;
import com.tinet.clink.ticket.response.ListTicketResponse;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author zhangpc
 * @since 2024/06/05
 */
public class ChatTest extends AbstractTest  {



    @Test
    public void chatListConversations() {

        ClientConfiguration configuration = new ClientConfiguration(
                "2d7fe04571cc95449b5267d57e334ab7",           // AccessKeyId
                "456v5g3rdR57yc7H38hJ");     // AccessKeySecret
        configuration.setHost("api-bj.clink.cn");
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
                "2d7fe04571cc95449b5267d57e334ab7",           // AccessKeyId
                "456v5g3rdR57yc7H38hJ");     // AccessKeySecret
        configuration.setHost("api-bj.clink.cn");
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
                "2d7fe04571cc95449b5267d57e334ab7",           // AccessKeyId
                "456v5g3rdR57yc7H38hJ");     // AccessKeySecret
        configuration.setHost("api-bj.clink.cn");
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
                "2d7fe04571cc95449b5267d57e334ab7",           // AccessKeyId
                "456v5g3rdR57yc7H38hJ");     // AccessKeySecret
        configuration.setHost("api-bj.clink.cn");
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
                "2d7fe04571cc95449b5267d57e334ab7",           // AccessKeyId
                "456v5g3rdR57yc7H38hJ");     // AccessKeySecret
        configuration.setHost("api-bj.clink.cn");
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
