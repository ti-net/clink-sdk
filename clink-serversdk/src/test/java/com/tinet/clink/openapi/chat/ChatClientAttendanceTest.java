package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.chat.StatChatClientAttendanceRequest;
import com.tinet.clink.openapi.response.chat.StatChatClientAttendanceResponse;
import org.junit.Test;

public class ChatClientAttendanceTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void statClientAttendanceTest() throws ServerException, ClientException, JsonProcessingException {
        StatChatClientAttendanceRequest request = new StatChatClientAttendanceRequest();
        request.setStartTime("20220901");
        request.setEndTime("20220901");
        String[] strings = {"0001","0002"};
        request.setCnos(strings);
        request.setSortAsc(true);
        ClientConfiguration clientConfiguration = new ClientConfiguration("************", "************");
        clientConfiguration.setScheme("https");
        clientConfiguration.setHost("api-bj.clink.cn");
        Client client = new Client(clientConfiguration);
        StatChatClientAttendanceResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getStatChatClientAttendance()));
    }
}
