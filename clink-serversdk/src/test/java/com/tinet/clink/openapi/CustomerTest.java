package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.crm.request.customer.ListCustomerFieldRequest;
import com.tinet.clink.crm.response.customer.ListCustomerFieldResponse;
import org.junit.Test;

public class CustomerTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void listCustomerField() throws ServerException, ClientException {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "AK",          // AccessKeyId
                "SK");     // AccessKeySecret
        configuration.setHost("host");
        Client client = new Client(configuration);

        // 本接口无特有请求参数
        ListCustomerFieldRequest request = new ListCustomerFieldRequest();
        // 发起接口调用
        ListCustomerFieldResponse response = client.getResponseModel(request);

        try {
            String resultStr = mapper.writeValueAsString(response);
            System.out.println(resultStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
