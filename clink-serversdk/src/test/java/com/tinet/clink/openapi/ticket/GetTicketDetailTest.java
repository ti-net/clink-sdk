package com.tinet.clink.openapi.ticket;

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

        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
        "AK",          // AccessKeyId
        "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);


        // 创建请求request
        GetTicketDetailRequest getTicketDetailRequest = new GetTicketDetailRequest();
        getTicketDetailRequest.setExternalId("1234");

        GetTicketDetailResponse getTicketDetailResponse;

        try {
            getTicketDetailResponse = client.getResponseModel(getTicketDetailRequest);
            System.out.println(getTicketDetailResponse.getTicketDetail().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}