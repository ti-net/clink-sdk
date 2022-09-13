package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.request.ListTicketRequest;
import com.tinet.clink.ticket.response.ListTicketResponse;
import org.junit.Test;

/**
 *
 * @author liuhy
 * @date: 2020/8/25
 **/
public class ListTicketTest extends AbstractTest {

    @Test
    public void listTicket(){

        ClientConfiguration configuration = new ClientConfiguration(
                "a307c59f2e9e95b236aaded45806366c",          // AccessKeyId
                "55xWk0s47070pZK82e0w");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("http");
        Client client = new Client(configuration);

        ListTicketRequest listTicketRequest= new ListTicketRequest();

        listTicketRequest.setStartTime("2022-07-14 00:00:00");
        listTicketRequest.setEndTime("2022-07-14 23:59:59");
        listTicketRequest.setOffset(0);
        listTicketRequest.setLimit(10);
        try {
            ListTicketResponse response= client.getResponseModel(listTicketRequest);

            ObjectMapper objectMapper = new ObjectMapper();

            System.out.println(objectMapper.writeValueAsString(response));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}