package com.tinet.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.DescribeQueueRequest;
import com.tinet.clink.openapi.request.ListClientsRequest;
import com.tinet.clink.openapi.request.ListQueuesRequest;
import com.tinet.clink.openapi.request.ListQueuesWithAgentActionRequest;
import com.tinet.clink.openapi.request.cdr.ListCdrObsRequest;
import com.tinet.clink.openapi.request.chat.ChatRecordRequest;
import com.tinet.clink.openapi.request.chat.ListChatMessageRequest;
import com.tinet.clink.openapi.response.DescribeQueueResponse;
import com.tinet.clink.openapi.response.ListClientsResponse;
import com.tinet.clink.openapi.response.ListQueuesResponse;
import com.tinet.clink.openapi.response.ListQueuesWithAgentActionResponse;
import com.tinet.clink.openapi.response.cdr.ListCdrObsResponse;
import com.tinet.clink.openapi.response.chat.ChatMessageResponse;
import com.tinet.clink.openapi.response.chat.ChatRecordResponse;
import org.junit.Test;

/**
 * @author tinet-yuhq
 * @description
 * @createTime 2022/3/14 10:28
 */

public class test {
    Client client = new Client(new ClientConfiguration("1", "2"));
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void test() throws JsonProcessingException, ServerException, ClientException {

        ChatRecordRequest request = new ChatRecordRequest();
//        request.setStartDate("20220225");
        request.setEndDate("20220225");
        request.setCname("dingyi");
//        request.setCno("1228");
//        request.setLimit(90);
        ChatRecordResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(responseModel));
    }

    @Test
    public void testClients() throws Exception {
        ListClientsRequest request = new ListClientsRequest();
        request.setCno("5201");
        ListClientsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testMessage() throws Exception {
        ListChatMessageRequest request = new ListChatMessageRequest();
        request.setMainUniqueId("e09c127d-77e6-45a7-a6bd-ecf136496c39.1649296681");
        request.setDate("20220407");
        ChatMessageResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testIb() throws Exception {
        ListCdrObsRequest request = new ListCdrObsRequest();
        request.setStartDate("20220201");
        request.setEndDate("20220228");
        request.setLimit(20);
        request.setOffset(6);
        request.setCname("wangll");
        request.setCno("6666");
        ListCdrObsResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testQueueList() throws Exception {
        ListQueuesRequest request = new ListQueuesRequest();
        ListQueuesResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testQueueDetail() throws Exception {
        DescribeQueueRequest request = new DescribeQueueRequest();
        request.setQno("0001");
        DescribeQueueResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void testQueueMember() throws Exception {
        ListQueuesWithAgentActionRequest request = new ListQueuesWithAgentActionRequest();
        request.setCno("1000");
        ListQueuesWithAgentActionResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response));
    }
}
