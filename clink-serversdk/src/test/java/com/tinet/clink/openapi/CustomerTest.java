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
import com.tinet.clink.crm.request.customer.ListCustomerLabelsRequest;
import com.tinet.clink.crm.request.customer.QueryCustomerRequest;
import com.tinet.clink.crm.response.customer.CreateCustomerResponse;
import com.tinet.clink.crm.response.customer.ListCustomerFieldResponse;
import com.tinet.clink.crm.response.customer.ListCustomerLabelsResponse;
import com.tinet.clink.crm.response.customer.QueryCustomerResponse;
import org.junit.Test;

public class CustomerTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void listCustomerField() throws ServerException, ClientException {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "9e969f413431ae0c0cf674fc56cec6ad",          // AccessKeyId
                "9616O59uJ3Whmp001704");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
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



    @Test
    public void CreateCustomer() throws ServerException, ClientException {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "9e969f413431ae0c0cf674fc56cec6ad",          // AccessKeyId
                "9616O59uJ3Whmp001704");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        IdValue a = new IdValue();
        a.setName("自定义级联");
        a.setValue("测试33");
        IdValue b = new IdValue();
        b.setName("下拉1");
        b.setValue("1");
        IdValue c = new IdValue();
        c.setName("单选2");//1,2,3,4
        c.setValue("4");
        IdValue d = new IdValue();
        d.setName("复选1");
        d.setValue("1,2,3");
        IdValue e = new IdValue();
        e.setName("身份证测试");
        e.setValue("110101199607288003");
        IdValue f = new IdValue();
        f.setName("0519电话号码");
        f.setValue("123");

        // 本接口无特有请求参数
        CreateCustomerRequest request = new CreateCustomerRequest();
        IdValue[] customize =  new IdValue[] {a,b,c,d,e,f};
        request.setName("gxd03131558");
        request.setRemark("11");
        request.setCustomize(customize);
        request.setShareType(0);
        request.setTel(new String[]{"18812312312"});

        // 发起接口调用
        CreateCustomerResponse response = client.getResponseModel(request);

        try {
            String resultStr = mapper.writeValueAsString(response);
            System.out.println(resultStr);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }
    @Test
    public void QueryCustomer() throws ServerException, ClientException {
//        // 创建访问服务的client实例并初始化
//        ClientConfiguration configuration = new ClientConfiguration(
//                "9e969f413431ae0c0cf674fc56cec6ad",          // AccessKeyId
//                "9616O59uJ3Whmp001704");     // AccessKeySecret
//        configuration.setHost("api-bj-test0.clink.cn");
//        configuration.setScheme("https");


        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "f7938cbf85fbc6ee82f9120386a62d6c",          // AccessKeyId
                "195Hf4XQtg082X02776Z");     // AccessKeySecret
        configuration.setHost("localhost");
        configuration.setScheme("http");
        configuration.setPort(8090);
        Client client = new Client(configuration);

        // 本接口无特有请求参数
        QueryCustomerRequest request = new QueryCustomerRequest();
        request.setCustomerId(2201563);
        // 发起接口调用
        QueryCustomerResponse response = client.getResponseModel(request);

        try {
            String resultStr = mapper.writeValueAsString(response);
            System.out.println(resultStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void QueryCustomerLabels() throws ServerException, ClientException {
//        // 创建访问服务的client实例并初始化
//        ClientConfiguration configuration = new ClientConfiguration(
//                "9e969f413431ae0c0cf674fc56cec6ad",          // AccessKeyId
//                "9616O59uJ3Whmp001704");     // AccessKeySecret
//        configuration.setHost("api-bj-test0.clink.cn");
//        configuration.setScheme("https");


        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "f7938cbf85fbc6ee82f9120386a62d6c",          // AccessKeyId
                "195Hf4XQtg082X02776Z");     // AccessKeySecret
        configuration.setHost("localhost");
        configuration.setScheme("http");
        configuration.setPort(8090);
        Client client = new Client(configuration);

        // 本接口无特有请求参数
        ListCustomerLabelsRequest request = new ListCustomerLabelsRequest();
        // 发起接口调用
        ListCustomerLabelsResponse response = client.getResponseModel(request);

        try {
            String resultStr = mapper.writeValueAsString(response);
            System.out.println(resultStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
