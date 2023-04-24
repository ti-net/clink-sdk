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
        request.setStartTime("2023-04-21 00:00");
        request.setEndTime("2023-04-21 23:59");
        List<String> cnos = new ArrayList<>();
        cnos.add("0920");
        cnos.add("0319");
        List<Integer> appType = new ArrayList<>();
        appType.add(1);
        appType.add(2);
        request.setAppType(appType);
        request.setCnos(cnos);
        request.setSortAsc(true);
        ClientConfiguration clientConfiguration = new ClientConfiguration("2c5f97f4f332eecb6a8f02fb13a114d0", "AezzuG932H7pxDgY3WZY");
        clientConfiguration.setScheme("http");
        clientConfiguration.setHost("clink2-openapi-dev.clink.cn");
        Client client = new Client(clientConfiguration);
        StatChatClientWorkQualityResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getStatChatClientWorkQuality()));
    }
}
