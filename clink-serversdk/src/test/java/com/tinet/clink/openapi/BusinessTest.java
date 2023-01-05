package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.crm.request.GetBusinessRequest;
import com.tinet.clink.crm.request.ListBusinessCountRequest;
import com.tinet.clink.crm.request.ListBusinessFieldRequest;
import com.tinet.clink.crm.request.ListBusinessRequest;
import com.tinet.clink.crm.response.GetBusinessResponse;
import com.tinet.clink.crm.response.ListBusinessCountResponse;
import com.tinet.clink.crm.response.ListBusinessFieldResponse;

import com.tinet.clink.core.exceptions.ServerException;

import com.tinet.clink.crm.response.ListBusinessResponse;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiangyang
 * @date 2019/11/12
 */
public class BusinessTest extends AbstractTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void listCutomizeField() throws ServerException, ClientException {

        ListBusinessFieldRequest customerParamRequest = new ListBusinessFieldRequest();
        ListBusinessFieldResponse customerParamResponse = client.getResponseModel(customerParamRequest);

        if (customerParamResponse != null && customerParamResponse.getBusinessCustomizeParams() != null) {
            try {
                String resultStr = mapper.writeValueAsString(customerParamResponse);
                System.out.println(resultStr);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void listBusiness() throws ServerException, ClientException {


        //当前企业可以用的查询参数
        ListBusinessRequest listBusinessRequest = new ListBusinessRequest();

        listBusinessRequest.setStartTime(1595952000L);

        listBusinessRequest.setEndTime(1596124800L);

        Map<String, String> customize = new HashMap<String, String>();

        customize.put("25868","用车,出城,报备出城");


//        listBusinessRequest.setCustomize(customize);

        listBusinessRequest.setLimit(20);

        listBusinessRequest.setOffset(1);

        ListBusinessResponse listBusinessResponse = client.getResponseModel(listBusinessRequest);


    }

    @Test
    public void listBusinessCustomer() throws ServerException, ClientException {

        //开始查询业务记录自定义字段，拼装参数
        ListBusinessFieldRequest listBusinessFieldRequest = new ListBusinessFieldRequest();

        ListBusinessFieldResponse responseModel = client.getResponseModel(listBusinessFieldRequest);

        try {
            String resultStr = mapper.writeValueAsString(responseModel);
            System.out.println(resultStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getBusinessDetail() throws ServerException, ClientException {

        GetBusinessRequest businessRequest = new GetBusinessRequest();

        businessRequest.setId(121840);

        GetBusinessResponse response = client.getResponseModel(businessRequest);

        System.out.println(1);
    }



    @Test
    public void listBusinessCount() throws ServerException, ClientException {


        //当前企业可以用的查询参数
        ListBusinessCountRequest listBusinessRequestCount = new ListBusinessCountRequest();

        listBusinessRequestCount.setStartTime(1595952000L);

        listBusinessRequestCount.setEndTime(1596124800L);


        ListBusinessCountResponse listBusinessCountResponse = client.getResponseModel(listBusinessRequestCount);


    }



}
