package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.ticket.request.GetFormDetailRequest;
import com.tinet.clink.ticket.response.GetFormDetailResponse;
import org.junit.Test;

/**
 * 表单详情查询请求示例
 *
 * @date: 2020/11/25
 **/
public class GetFormDetailTest extends AbstractTest {

    @Test
    public void getFormDetail() {
        ClientConfiguration configuration = new ClientConfiguration(
                "babc37aa470a92102016186b1cb79a82",          // AccessKeyId
                "vtq672M5Y48aI3728m14");     // AccessKeySecret
        configuration.setHost("127.0.0.1");
        configuration.setScheme("http");
        configuration.setPort(8111);
        Client client = new Client(configuration);

        // 创建请求request
        GetFormDetailRequest getFormDetailRequest = new GetFormDetailRequest();
        getFormDetailRequest.setId(28999);

        try {
            GetFormDetailResponse getFormDetailResponse = client.getResponseModel(getFormDetailRequest);
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(objectMapper.writeValueAsString(getFormDetailResponse));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}