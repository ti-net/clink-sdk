package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.chat.StatChatClientWorkloadRequest;
import com.tinet.clink.openapi.request.chat.StatChatQueueWorkloadRequest;
import com.tinet.clink.openapi.response.chat.StatChatClientWorkloadResponse;
import com.tinet.clink.openapi.response.chat.StatChatQueueWorkloadResponse;
import org.junit.Test;

public class ChatClientWorkloadTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void statClientWorkloadTest() throws ServerException, ClientException, JsonProcessingException {
        StatChatClientWorkloadRequest request = new StatChatClientWorkloadRequest();
        request.setDate("20201124");

        ClientConfiguration clientConfiguration = new ClientConfiguration("b30cc01d53094b4c3e45454046433a21", "9ZaZE40957v01j7CK0f8");
        clientConfiguration.setScheme("http");
        clientConfiguration.setHost("api-bj-test5.clink.cn");
        Client client = new Client(clientConfiguration);
        StatChatClientWorkloadResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getStatChatClientWorkload()));

    }

    @Test
    public void statQueueWorkloadTest2() throws ServerException, ClientException, JsonProcessingException {
        StatChatQueueWorkloadRequest request2 = new StatChatQueueWorkloadRequest();
        request2.setDate("20201125");

        ClientConfiguration clientConfiguration = new ClientConfiguration("b30cc01d53094b4c3e45454046433a21", "9ZaZE40957v01j7CK0f8");
        clientConfiguration.setScheme("http");
        clientConfiguration.setHost("api-bj-test5.clink.cn");
        Client client = new Client(clientConfiguration);
        StatChatQueueWorkloadResponse response2 = client.getResponseModel(request2);
        System.out.println(mapper.writeValueAsString(response2.getStatChatQueueWorkload()));

    }

}
