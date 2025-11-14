package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.request.GetTicketDetailRequest;
import com.tinet.clink.ticket.response.GetTicketDetailResponse;
import org.junit.Test;

/**
 * 工单详情查询请求示例
 *
 * @date: 2020/11/25
 **/
public class GetTicketDetailTest extends AbstractTest {

    @Test
    public void getTicketDetail() {

        ClientConfiguration configuration = new ClientConfiguration(
                "c470fcc6f352e4fe56ce416a263711c1",          // AccessKeyId
                "JTq5O462Au735874rax4");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        // 创建请求request
        GetTicketDetailRequest getTicketDetailRequest = new GetTicketDetailRequest();
        getTicketDetailRequest.setId(68056);

        GetTicketDetailResponse getTicketDetailResponse;

        try {
            getTicketDetailResponse = client.getResponseModel(getTicketDetailRequest);
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(objectMapper.writeValueAsString(getTicketDetailResponse));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}