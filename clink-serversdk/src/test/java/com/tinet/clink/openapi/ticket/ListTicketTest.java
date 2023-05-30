package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.request.ListTicketRequest;
import com.tinet.clink.ticket.response.ListTicketResponse;
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
                "3ee29817e02c59ab4f27f55b75053ab9",          // AccessKeyId
                "4498PzDejj8y13e2u19Q");     // AccessKeySecret
        configuration.setHost("alb-01l5fw2u4lg0sajop3.cn-beijing.alb.aliyuncs.com");
        configuration.setScheme("http");
        Client client = new Client(configuration);

        ListTicketRequest listTicketRequest= new ListTicketRequest();

        listTicketRequest.setStartTime("2022-07-20 00:00:00");
        listTicketRequest.setEndTime("2022-10-19 23:59:59");
        listTicketRequest.setCloseTimeStart("2022-07-20 00:00:00");
        listTicketRequest.setCloseTimeEnd("2022-10-19 23:59:59");
//        listTicketRequest.setEndTimeStart("2022-07-20 00:00:00");
//        listTicketRequest.setEndTimeEnd("2022-10-19 23:59:59");
        listTicketRequest.setOffset(0);
        listTicketRequest.setLimit(10);
        HashMap<String, String> fieldMap = new HashMap<>();
        fieldMap.put("93915", "43dd");

        HashMap<String, String> systemFieldMap = new HashMap<>();
        systemFieldMap.put("866872", "1");
//        listTicketRequest.setFields(fieldMap);
//        listTicketRequest.setSystemFields(systemFieldMap);

        try {
            ListTicketResponse response= client.getResponseModel(listTicketRequest);

            ObjectMapper objectMapper = new ObjectMapper();

            System.out.println(objectMapper.writeValueAsString(response));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}