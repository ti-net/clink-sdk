package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.config.tel.restrict.CreateTelRestrictRequest;
import com.tinet.clink.openapi.request.config.tel.restrict.DeleteTelRestrictRequest;
import com.tinet.clink.openapi.request.config.tel.restrict.DescribeTelRestrictSettingRequest;
import com.tinet.clink.openapi.request.config.tel.restrict.ListTelRestrictRequest;
import com.tinet.clink.openapi.request.config.tel.restrict.UpdateTelRestrictSettingRequest;
import com.tinet.clink.openapi.response.config.tel.restrict.CreateTelRestrictResponse;
import com.tinet.clink.openapi.response.config.tel.restrict.DeleteTelRestrictResponse;
import com.tinet.clink.openapi.response.config.tel.restrict.DescribeTelRestrictSettingResponse;
import com.tinet.clink.openapi.response.config.tel.restrict.ListTelRestrictResponse;
import com.tinet.clink.openapi.response.config.tel.restrict.UpdateTelRestrictSettingResponse;
import org.junit.Test;

import java.util.Date;

/**
 * @author libin
 * @date 2021-12-13 2:16 下午
 */
public class TelRestrictTest extends AbstractTest {

    @Test
    public void listTest() throws ServerException, ClientException, JsonProcessingException {
        ListTelRestrictRequest request = new ListTelRestrictRequest();

        request.setOffset(0);
        request.setLimit(10);

        request.setRestrictType(2);
        ListTelRestrictResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseModel));
    }

    @Test
    public void createTest() throws ServerException, ClientException, JsonProcessingException {
        CreateTelRestrictRequest request = new CreateTelRestrictRequest();

        request.setTel("16600998865");
        request.setTelType(1);
        request.setType(1);
        request.setRestrictType(1);
        request.setExpirationTime(new Date());

        CreateTelRestrictResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseModel));
    }

    @Test
    public void delTest() throws ServerException, ClientException, JsonProcessingException {
        DeleteTelRestrictRequest request = new DeleteTelRestrictRequest();

        request.setTel("16600998865");
        request.setRestrictType(1);

        DeleteTelRestrictResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseModel));
    }

    @Test
    public void listSettingTest() throws JsonProcessingException, ServerException, ClientException {
            DescribeTelRestrictSettingRequest request = new DescribeTelRestrictSettingRequest();
            DescribeTelRestrictSettingResponse responseModel = client.getResponseModel(request);
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseModel));


    }

    @Test
    public void updateSettingTest() throws ServerException, ClientException, JsonProcessingException {
        UpdateTelRestrictSettingRequest request = new UpdateTelRestrictSettingRequest();

        request.setSettingValue(3);
        UpdateTelRestrictSettingResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseModel));
    }
}