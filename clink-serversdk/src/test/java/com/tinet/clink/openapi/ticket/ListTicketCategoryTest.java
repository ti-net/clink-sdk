package com.tinet.clink.openapi.ticket;

import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;

import com.tinet.clink.ticket.request.ListTicketCategoryRequest;
import com.tinet.clink.ticket.response.ListTicketCategoryResponse;
import org.junit.Test;

/**
 * 工单模板类别请求示例
 *
 * @date: 2020/11/25
 **/
public class ListTicketCategoryTest extends AbstractTest {

    @Test
    public void listTicketCategory() {

        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);

        // 创建请求request
        ListTicketCategoryRequest listTicketCategoryRequest = new ListTicketCategoryRequest();

        ListTicketCategoryResponse listTicketCategoryResponse;

        try {
            listTicketCategoryResponse = client.getResponseModel(listTicketCategoryRequest);
            System.out.println(listTicketCategoryResponse.getCategoryModels().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}