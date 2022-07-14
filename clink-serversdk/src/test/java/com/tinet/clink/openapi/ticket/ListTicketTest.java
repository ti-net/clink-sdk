package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.request.ticket.ListTicketRequest;
import com.tinet.clink.openapi.response.ticket.ListTicketResponse;
import org.junit.Test;

/**
 *
 * @author liuhy
 * @date: 2020/8/25
 **/
public class ListTicketTest extends AbstractTest {

    @Test
    public void listTicket(){

        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "b521465079b08d07b73f54e2f5845f95",          // AccessKeyId
                "8Qe3qaN322Vp3260i288");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("http");
        Client client = new Client(configuration);

        ListTicketRequest listTicketRequest = new ListTicketRequest();

        listTicketRequest.setStartTime("2020-05-01 00:00:00");
        listTicketRequest.setEndTime("2020-08-10 23:59:59");
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