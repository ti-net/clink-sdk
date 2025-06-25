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
                "657c8b11f7ae9bc9b51ec67747fc24f5",          // AccessKeyId
                "5924A00GL81m97NB0zH9");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        ListTicketRequest listTicketRequest= new ListTicketRequest();
        //listTicketRequest.setSource(1);
        listTicketRequest.setStartTime("2025-05-26 00:00:00");
        listTicketRequest.setEndTime("2025-06-25 23:59:59");
        listTicketRequest.setUpdateTimeStart("2025-05-26 00:00:00");
        listTicketRequest.setUpdateTimeEnd("2025-06-25 23:59:59");
        //listTicketRequest.setCustomerId(5575461);
        listTicketRequest.setCustomerName("沈忱测试");
        listTicketRequest.setCustomerTel("13700000000");
        try {
            ListTicketResponse response= client.getResponseModel(listTicketRequest);

            ObjectMapper objectMapper = new ObjectMapper();

            System.out.println(objectMapper.writeValueAsString(response));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}