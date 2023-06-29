package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.cc.request.numbers.ListClidNumbersRequest;
import com.tinet.clink.cc.request.numbers.ListDynamicTelGroupRuleRequest;
import com.tinet.clink.cc.request.numbers.ListHotlineNumbersRequest;
import com.tinet.clink.cc.response.numbers.ListClidNumbersResponse;
import com.tinet.clink.cc.response.numbers.ListDynamicTelGroupRuleResponse;
import com.tinet.clink.cc.response.numbers.ListHotlineNumbersResponse;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;

import org.junit.Test;

/**
 * 号码设置功能测试类
 *
 * @author wangyq
 * @date 2018/10/26
 */
public class NumberTest extends AbstractTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void listClidNumbersTest() throws ServerException, ClientException, JsonProcessingException {
        ListClidNumbersRequest listClidNumbersRequest = new ListClidNumbersRequest();
        ListClidNumbersResponse response = client.getResponseModel(listClidNumbersRequest);

        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void listHotlineNumbersTest() throws ServerException, ClientException, JsonProcessingException {
        ListHotlineNumbersRequest listHotlineNumbersRequest = new ListHotlineNumbersRequest();
        ListHotlineNumbersResponse response = client.getResponseModel(listHotlineNumbersRequest);

        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void listDynamicTelGroupRuleTest() throws ServerException, ClientException, JsonProcessingException {
        ListDynamicTelGroupRuleRequest listDynamicTelGroupRuleRequest = new ListDynamicTelGroupRuleRequest();
        ListDynamicTelGroupRuleResponse response = client.getResponseModel(listDynamicTelGroupRuleRequest);

        System.out.println(mapper.writeValueAsString(response));
    }
}