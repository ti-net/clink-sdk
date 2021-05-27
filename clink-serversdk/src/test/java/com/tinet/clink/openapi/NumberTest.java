package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.added.ListNumberAppealRequest;
import com.tinet.clink.openapi.request.config.numbers.ListClidNumbersRequest;
import com.tinet.clink.openapi.request.config.numbers.ListHotlineNumbersRequest;
import com.tinet.clink.openapi.response.ResponseModel;
import com.tinet.clink.openapi.response.config.numbers.ListClidNumbersResponse;
import com.tinet.clink.openapi.response.config.numbers.ListHotlineNumbersResponse;
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
    public void NumberAppealTest(){
        // todo need test
        ListNumberAppealRequest request  = new ListNumberAppealRequest();
        request.setStartTime("2021-05-01 00:00:00");
        request.setEndTime("2021-05-31 00:00:00");
        try {
            ResponseModel response = client.getResponseModel(request);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}