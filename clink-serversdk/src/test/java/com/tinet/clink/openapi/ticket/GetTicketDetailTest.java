package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.ticket.GetTicketDetailRequest;
import com.tinet.clink.openapi.request.ticket.ListTicketCategoryRequest;
import com.tinet.clink.openapi.response.ticket.GetTicketDetailResponse;
import com.tinet.clink.openapi.response.ticket.ListTicketCategoryResponse;
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
                "b521465079b08d07b73f54e2f5845f95",          // AccessKeyId
                "8Qe3qaN322Vp3260i288");     // AccessKeySecret
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