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
                "a307c59f2e9e95b236aaded45806366c",          // AccessKeyId
                "55xWk0s47070pZK82e0w");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("http");
        Client client = new Client(configuration);

        // 创建请求request
        GetTicketDetailRequest getTicketDetailRequest = new GetTicketDetailRequest();
        getTicketDetailRequest.setId(171269);

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