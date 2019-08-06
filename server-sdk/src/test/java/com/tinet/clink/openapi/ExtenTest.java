package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.config.exten.*;
import com.tinet.clink.openapi.response.config.exten.*;
import org.junit.Test;

/**
 * 话机设置测试类
 *
 * @author wangyq
 * @date 2018/10/26
 */
public class ExtenTest extends AbstractTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createExtenTest() throws ServerException, ClientException, JsonProcessingException {
        CreateExtenRequest createExtenRequest = new CreateExtenRequest();
        createExtenRequest.setExtenNumber("011114");
        createExtenRequest.setPassword("111");
        createExtenRequest.setAreaCode("0234");
        createExtenRequest.setType(1);
        createExtenRequest.setAllow(2);

        CreateExtenResponse response = client.getResponseModel(createExtenRequest);

        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void updateExtenTest() throws ServerException, ClientException, JsonProcessingException {
        UpdateExtenRequest updateExtenRequest = new UpdateExtenRequest();
        updateExtenRequest.setExtenNumber("012");
        updateExtenRequest.setAreaCode("010");

        UpdateExtenResponse updateExtenResponse = client.getResponseModel(updateExtenRequest);

        System.out.println(mapper.writeValueAsString(updateExtenResponse));
    }

    @Test
    public void listExtenTest() throws ServerException, ClientException, JsonProcessingException {
        ListExtensRequest listExtensRequest = new ListExtensRequest();
        listExtensRequest.setAreaCode("010");

        ListExtensResponse response = client.getResponseModel(listExtensRequest);

        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
    }

    @Test
    public void describeExtenTest() throws ServerException, ClientException, JsonProcessingException {
        DescribeExtenRequest describeExtenRequest = new DescribeExtenRequest();
        describeExtenRequest.setExtenNumber("7788");

        DescribeExtenResponse response = client.getResponseModel(describeExtenRequest);

        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void deleteExtenTest() throws ServerException, ClientException {
        DeleteExtenRequest deleteExtenRequest = new DeleteExtenRequest();
        deleteExtenRequest.setExtenNumber("011112");

        DeleteExtenResponse response = client.getResponseModel(deleteExtenRequest);

        System.out.println(response.getExten());
    }
}