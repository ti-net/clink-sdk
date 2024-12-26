package com.tinet.clink.openapi.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.livechat.request.StatInvestigationCnoPageRequest;
import com.tinet.clink.livechat.request.StatQueuePeriodListRequest;
import com.tinet.clink.livechat.request.StatQueueQnoPageRequest;
import com.tinet.clink.livechat.response.StatInvestigationCnoPageResponse;
import com.tinet.clink.livechat.response.StatQueuePeriodListResponse;
import com.tinet.clink.livechat.response.StatQueueQnoPageResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class ChatStatTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void statInvestigationCnoPageTest() throws ServerException, ClientException, JsonProcessingException {
        StatInvestigationCnoPageRequest request = new StatInvestigationCnoPageRequest();
        request.setStartTime("");
        request.setEndTime("");
        List<Integer> contactTypes = new ArrayList<>();
        request.setContactTypes(contactTypes);
        List<String> cnos = new ArrayList<>();
        request.setCnos(cnos);
        List<String> qnos = new ArrayList<>();
        request.setQnos(qnos);
        request.setPageIndex(1);
        request.setPageSize(10);
        ClientConfiguration clientConfiguration = new ClientConfiguration("b30cc01d53094b4c3e45454046433a21", "9ZaZE40957v01j7CK0f8");
        clientConfiguration.setScheme("http");
        clientConfiguration.setHost("api-bj-test5.clink.cn");
        Client client = new Client(clientConfiguration);
        StatInvestigationCnoPageResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getData()));
    }

    @Test
    public void statQueuePeriodListTest() throws ServerException, ClientException, JsonProcessingException {
        StatQueuePeriodListRequest request = new StatQueuePeriodListRequest();
        request.setStartTime("");
        request.setEndTime("");
        List<Integer> contactTypes = new ArrayList<>();
        request.setContactTypes(contactTypes);
        List<String> qnos = new ArrayList<>();
        request.setQnos(qnos);
        request.setPeriodType(1);
        ClientConfiguration clientConfiguration = new ClientConfiguration("b30cc01d53094b4c3e45454046433a21", "9ZaZE40957v01j7CK0f8");
        clientConfiguration.setScheme("http");
        clientConfiguration.setHost("api-bj-test5.clink.cn");
        Client client = new Client(clientConfiguration);
        StatQueuePeriodListResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getData()));
    }


    @Test
    public void statQueueQnoPageTest() throws ServerException, ClientException, JsonProcessingException {
        StatQueueQnoPageRequest request = new StatQueueQnoPageRequest();
        request.setStartTime("");
        request.setEndTime("");
        List<Integer> appType = new ArrayList<>();
        appType.add(1);
        request.setAppType(appType);
        List<String> qnos = new ArrayList<>();
        request.setQnos(qnos);
        request.setPageIndex(1);
        request.setPageSize(10);
        ClientConfiguration clientConfiguration = new ClientConfiguration("b30cc01d53094b4c3e45454046433a21", "9ZaZE40957v01j7CK0f8");
        clientConfiguration.setScheme("http");
        clientConfiguration.setHost("api-bj-test5.clink.cn");
        Client client = new Client(clientConfiguration);
        StatQueueQnoPageResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getData()));
    }
}
