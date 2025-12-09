package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import com.tinet.clink.crm.model.IdValue;
import com.tinet.clink.crm.request.customer.CreateCustomerRequest;
import com.tinet.clink.crm.request.customer.ListCustomerFieldRequest;
import com.tinet.clink.crm.request.customer.ListCustomerRequest;
import com.tinet.clink.crm.request.customer.QueryCustomerRequest;
import com.tinet.clink.crm.response.customer.CreateCustomerResponse;
import com.tinet.clink.crm.response.customer.ListCustomerFieldResponse;
import com.tinet.clink.crm.response.customer.ListCustomerResponse;
import com.tinet.clink.crm.response.customer.QueryCustomerResponse;
import org.junit.Test;

public class ListCustomerTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void listCustomers() throws ServerException, ClientException {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "7ca7a871c0b0eeb1bf356449931d3866",          // AccessKeyId
                "Q6M35169p00893V6314T");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        // 本接口无特有请求参数
        ListCustomerRequest request = new ListCustomerRequest();
        // 发起接口调用
        request.setFirstContactStartTime(1757952000L);
        request.setFirstContactEndTime(1760544000L);
        request.setFirstChatContactEndTime(1760544000L);
        request.setFirstChatContactStartTime(1757952000L);
        request.setLastChatContactStartTime(1757952000L);
        request.setLastChatContactEndTime(1760544000L);
        ListCustomerResponse response = client.getResponseModel( request);
        System.out.println(response);
    }

}
