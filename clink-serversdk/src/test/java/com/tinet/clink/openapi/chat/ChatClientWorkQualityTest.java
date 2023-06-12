package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.livechat.request.StatChatClientWorkQualityRequest;
import com.tinet.clink.livechat.response.StatChatClientWorkQualityResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class ChatClientWorkQualityTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void statClientWorkQualityTest() throws JsonProcessingException, ServerException, ClientException {
        StatChatClientWorkQualityRequest request = new StatChatClientWorkQualityRequest();
        request.setStartTime("2023-04-27 00:00");
        request.setEndTime("2023-04-27 23:59");
        List<String> cnos = new ArrayList<>();
        List<Integer> appType = new ArrayList<>();
//        appType.add(2);
        request.setAppType(appType);
        request.setCnos(cnos);
        request.setSortAsc(false);
        ClientConfiguration clientConfiguration = new ClientConfiguration("7b24c27454505ba6ed05a9e80ba5f1db", "h4J815EdG99rI6W1K0tq");
        clientConfiguration.setScheme("http");
        clientConfiguration.setHost("alb-01l5fw2u4lg0sajop3.cn-beijing.alb.aliyuncs.com");
        Client client = new Client(clientConfiguration);
        StatChatClientWorkQualityResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }
}
