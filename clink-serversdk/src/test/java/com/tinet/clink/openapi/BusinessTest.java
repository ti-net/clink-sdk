package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.tinet.clink.core.client.Client;
import com.tinet.clink.core.client.ClientConfiguration;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.crm.request.*;
import com.tinet.clink.crm.response.*;

import com.tinet.clink.core.exceptions.ServerException;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jiangyang
 * @date 2019/11/12
 */
public class BusinessTest extends AbstractTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void listCustomizeField() throws ServerException, ClientException {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "ac9d9658fecd3cd64cec4324d59fb88f",          // AccessKeyId
                "J402964Em6c4b22c3XQL");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        ListBusinessFieldRequest customerParamRequest = new ListBusinessFieldRequest();

        // 发起接口调用
        ListBusinessFieldResponse response = client.getResponseModel(customerParamRequest);
        try {
            String resultStr = mapper.writeValueAsString(response);
            System.out.println(resultStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listBusiness() throws ServerException, ClientException {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "ac9d9658fecd3cd64cec4324d59fb88f",          // AccessKeyId
                "J402964Em6c4b22c3XQL");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        //当前企业可以用的查询参数
        ListBusinessRequest listBusinessRequest = new ListBusinessRequest();
        listBusinessRequest.setStartTime(1689868800L);
        listBusinessRequest.setEndTime(1689955199L);
        listBusinessRequest.setUpdateStartTime(1689904800L);
        listBusinessRequest.setUpdateEndTime(1689908400L);
        listBusinessRequest.setCustomerId(5209110);
        listBusinessRequest.setCallId("callId-A");
        listBusinessRequest.setChatId("chatId-A");
        listBusinessRequest.setSource(30);
        listBusinessRequest.setCustomerTel("10086");
        listBusinessRequest.setTel("10086");
        listBusinessRequest.setCustomerName("sdk");
        Map<String, String> customize = new HashMap<>();
        customize.put("158571", "备注");
        listBusinessRequest.setCustomize(customize);

        listBusinessRequest.setLimit(20);
        listBusinessRequest.setOffset(0);

        // 发起接口调用
        ListBusinessResponse response = client.getResponseModel(listBusinessRequest);
        try {
            String resultStr = mapper.writeValueAsString(response);
            System.out.println(resultStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getBusinessDetail() throws ServerException, ClientException {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "ac9d9658fecd3cd64cec4324d59fb88f",          // AccessKeyId
                "J402964Em6c4b22c3XQL");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        GetBusinessRequest businessRequest = new GetBusinessRequest();
        businessRequest.setId(2142);

        // 发起接口调用
        GetBusinessResponse response = client.getResponseModel(businessRequest);
        try {
            String resultStr = mapper.writeValueAsString(response);
            System.out.println(resultStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listBusinessCount() throws ServerException, ClientException {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "ac9d9658fecd3cd64cec4324d59fb88f",          // AccessKeyId
                "J402964Em6c4b22c3XQL");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        //当前企业可以用的查询参数
        ListBusinessCountRequest listBusinessRequestCount = new ListBusinessCountRequest();
        listBusinessRequestCount.setStartTime(1689868800L);
        listBusinessRequestCount.setEndTime(1689955199L);
        listBusinessRequestCount.setUpdateStartTime(1689904800L);
        listBusinessRequestCount.setUpdateEndTime(1689908400L);
        listBusinessRequestCount.setCustomerId(5209110);
        listBusinessRequestCount.setCallId("callId-A");
        listBusinessRequestCount.setChatId("chatId-A");
        listBusinessRequestCount.setSource(30);
        listBusinessRequestCount.setCustomerTel("10086");
        listBusinessRequestCount.setTel("10086");
        listBusinessRequestCount.setCustomerName("sdk");
        Map<String, String> customize = new HashMap<>();
        customize.put("158571", "备注");
        listBusinessRequestCount.setCustomize(customize);

        // 发起接口调用
        ListBusinessCountResponse response = client.getResponseModel(listBusinessRequestCount);
        try {
            String resultStr = mapper.writeValueAsString(response);
            System.out.println(resultStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void CreateBusiness() throws ServerException, ClientException {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "ac9d9658fecd3cd64cec4324d59fb88f",          // AccessKeyId
                "J402964Em6c4b22c3XQL");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        //创建业务记录
        CreateBusinessRequest createBusinessRequest = new CreateBusinessRequest();
        createBusinessRequest.setCustomerId(5209110);
        createBusinessRequest.setTopic("sdk业务记录创建");
        createBusinessRequest.setSource(30);
        try {
            ArrayNode customize = mapper.readTree("[{\"name\":\"备注\",\"value\":\"这是一条备注\"}]").deepCopy();
            createBusinessRequest.setCustomize(customize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        createBusinessRequest.setChatId("chatId-abc");
        createBusinessRequest.setChatStartTime(1689906232L);
        createBusinessRequest.setCallId("callId-abc");
        createBusinessRequest.setCallUniqueId("callUniqueId-abc");
        createBusinessRequest.setChatUniqueId("chatUniqueId-abc");

        // 发起接口调用
        CreateBusinessResponse response = client.getResponseModel(createBusinessRequest);
        try {
            String resultStr = mapper.writeValueAsString(response);
            System.out.println(resultStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listBusinessField() throws ServerException, ClientException {
        // 创建访问服务的client实例并初始化
        ClientConfiguration configuration = new ClientConfiguration(
                "9e969f413431ae0c0cf674fc56cec6ad",          // AccessKeyId
                "9616O59uJ3Whmp001704");     // AccessKeySecret
        configuration.setHost("api-bj-test0.clink.cn");
        configuration.setScheme("https");
        Client client = new Client(configuration);

        // 本接口无特有请求参数
        ListBusinessFieldInfoRequest request = new ListBusinessFieldInfoRequest();

        // 发起接口调用
        ListBusinessFieldInfoResponse response = client.getResponseModel(request);

        try {
            String resultStr = mapper.writeValueAsString(response);
            System.out.println(resultStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
