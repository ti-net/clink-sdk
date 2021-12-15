package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.config.enterprise.pause.AddEnterprisePausesRequest;
import com.tinet.clink.openapi.request.config.enterprise.pause.DelEnterprisePausesRequest;
import com.tinet.clink.openapi.request.config.enterprise.pause.ListEnterprisePausesRequest;
import com.tinet.clink.openapi.request.config.enterprise.pause.UpdateEnterprisePausesRequest;
import com.tinet.clink.openapi.response.config.enterprise.pause.AddEnterprisePausesResponse;
import com.tinet.clink.openapi.response.config.enterprise.pause.DelEnterprisePausesResponse;
import com.tinet.clink.openapi.response.config.enterprise.pause.ListEnterprisePausesResponse;
import com.tinet.clink.openapi.response.config.enterprise.pause.UpdateEnterprisePausesResponse;
import org.junit.Test;

public class EnterprisePauseTest extends AbstractTest {

    @Test
    public void list() {
        ListEnterprisePausesRequest request = new ListEnterprisePausesRequest();
        try {
            ListEnterprisePausesResponse response = client.getResponseModel(request);
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

        } catch (ClientException | ServerException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addTest() throws ServerException, ClientException, JsonProcessingException {
        AddEnterprisePausesRequest request = new AddEnterprisePausesRequest();
        request.setIsDefault(0);
        request.setIsRest(1);
        request.setPauseStatus("opt");
        AddEnterprisePausesResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseModel));
    }

    @Test
    public void updateTest() throws ServerException, ClientException, JsonProcessingException {
        UpdateEnterprisePausesRequest request = new UpdateEnterprisePausesRequest();
        request.setId(1708);
        request.setPauseStatus("ok1");
        request.setIsRest(1);
        request.setIsDefault(1);
        UpdateEnterprisePausesResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseModel));
    }

    @Test
    public void delTest() throws ServerException, ClientException, JsonProcessingException {
        DelEnterprisePausesRequest request = new DelEnterprisePausesRequest();
        request.setPauseStatus("ok1");
        DelEnterprisePausesResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseModel));
    }
}
