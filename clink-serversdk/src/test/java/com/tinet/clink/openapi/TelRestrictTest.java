package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.config.tel.restrict.CreateTelRestrictRequest;
import com.tinet.clink.openapi.request.config.tel.restrict.DelTelRestrictRequest;
import com.tinet.clink.openapi.request.config.tel.restrict.ListTelRestrictRequest;
import com.tinet.clink.openapi.request.config.tel.restrict.ListTelRestrictSettingRequest;
import com.tinet.clink.openapi.request.config.tel.restrict.UpdateTelRestrictSettingRequest;
import com.tinet.clink.openapi.response.config.tel.restrict.ListTelRestrictResponse;
import com.tinet.clink.openapi.response.config.tel.restrict.CreateTelRestrictResponse;
import com.tinet.clink.openapi.response.config.tel.restrict.DelTelRestrictResponse;
import com.tinet.clink.openapi.response.config.tel.restrict.ListTelRestrictSettingResponse;
import com.tinet.clink.openapi.response.config.tel.restrict.UpdateTelRestrictSettingResponse;
import org.junit.Test;

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

        CreateTelRestrictResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseModel));
    }

    @Test
    public void delTest() throws ServerException, ClientException, JsonProcessingException {
        DelTelRestrictRequest request = new DelTelRestrictRequest();

        request.setTel("16600998865");
        request.setRestrictType(1);

        DelTelRestrictResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseModel));
    }

    @Test
    public void listSettingTest() throws JsonProcessingException {
            ListTelRestrictSettingRequest request = new ListTelRestrictSettingRequest();
            ListTelRestrictSettingResponse responseModel = null;
            try {
                responseModel = client.getResponseModel(request);
            } catch (ClientException e) {
                e.printStackTrace();
            } catch (ServerException e) {
                e.printStackTrace();
            }
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
