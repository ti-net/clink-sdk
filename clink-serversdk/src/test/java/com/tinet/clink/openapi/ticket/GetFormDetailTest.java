package com.tinet.clink.openapi.ticket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.openapi.Client;
import com.tinet.clink.openapi.ClientConfiguration;
import com.tinet.clink.openapi.request.ticket.GetFormDetailRequest;
import com.tinet.clink.openapi.response.ticket.GetFormDetailResponse;
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
                "b521465079b08d07b73f54e2f5845f95",          // AccessKeyId
                "8Qe3qaN322Vp3260i288");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("http");
        Client client = new Client(configuration);

        // 创建请求request
        GetFormDetailRequest getFormDetailRequest = new GetFormDetailRequest();
        getFormDetailRequest.setId(5881);

        try {
            GetFormDetailResponse getFormDetailResponse = client.getResponseModel(getFormDetailRequest);
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(objectMapper.writeValueAsString(getFormDetailResponse));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}