package com.tinet.clink.openapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.crm.request.customer.ListCustomerRequest;
import com.tinet.clink.crm.request.groupcustomer.UpdateGroupCustomerRequest;
import com.tinet.clink.crm.response.customer.ListCustomerResponse;
import com.tinet.clink.crm.response.groupCustomer.UpdateGroupCustomerResponse;
import org.junit.Test;

public class UpdateCustomerTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void updateGroupCustomer() throws ServerException, ClientException {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "7ca7a871c0b0eeb1bf356449931d3866",          // AccessKeyId
                "Q6M35169p00893V6314T");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        // 本接口无特有请求参数
        UpdateGroupCustomerRequest request = new UpdateGroupCustomerRequest();
        // 发起接口调用
        request.setName("apiUpdate");
        request.setId(5607162);
        request.setRenovate(0);
        UpdateGroupCustomerResponse response = client.getResponseModel( request);
        System.out.println(response);
    }

}
