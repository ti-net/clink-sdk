package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.request.ticket.ListTicketRequest;
import com.tinet.clink.openapi.response.ticket.ListTicketResponse;
import org.junit.Test;

import java.util.HashMap;

/**
 *
 * @author liuhy
 * @date: 2020/8/25
 **/
public class ListTicketTest extends AbstractTest {

    @Test
    public void listTicket(){

        ClientConfiguration configuration = new ClientConfiguration(
                "85cd43bc929ee1a8eae6faa066239b8a",          // AccessKeyId
                "Et7GCR16765YC3T5i48V");     // AccessKeySecret
        configuration.setHost("alb-01l5fw2u4lg0sajop3.cn-beijing.alb.aliyuncs.com");
        configuration.setScheme("http");
        Client client = new Client(configuration);

        ListTicketRequest listTicketRequest = new ListTicketRequest();

        listTicketRequest.setStartTime("2022-07-20 00:00:00");
        listTicketRequest.setEndTime("2022-10-19 23:59:59");
        listTicketRequest.setOffset(0);
        listTicketRequest.setLimit(10);
        HashMap<String, String> fieldMap = new HashMap<>();
        fieldMap.put("93915", "43dd");

        HashMap<String, String> systemFieldMap = new HashMap<>();
        systemFieldMap.put("866872", "1");
//        listTicketRequest.setFields(fieldMap);
//        listTicketRequest.setSystemFields(systemFieldMap);

        listTicketRequest.setWorkflowIds(new Integer[]{1921, 2079});
        listTicketRequest.setTaskKey("UserTask_0cqajg6");
        try {
            ListTicketResponse response= client.getResponseModel(listTicketRequest);

            ObjectMapper objectMapper = new ObjectMapper();

            System.out.println(objectMapper.writeValueAsString(response));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}