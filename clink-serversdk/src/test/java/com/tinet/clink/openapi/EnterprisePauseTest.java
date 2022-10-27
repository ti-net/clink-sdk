package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.tinet.clink.cc.request.pause.CreateEnterprisePausesRequest;
import com.tinet.clink.cc.request.pause.DeleteEnterprisePausesRequest;
import com.tinet.clink.cc.request.pause.ListEnterprisePausesRequest;
import com.tinet.clink.cc.request.pause.UpdateEnterprisePausesRequest;
import com.tinet.clink.cc.response.pause.CreateEnterprisePausesResponse;
import com.tinet.clink.cc.response.pause.DeleteEnterprisePausesResponse;
import com.tinet.clink.cc.response.pause.ListEnterprisePausesResponse;
import com.tinet.clink.cc.response.pause.UpdateEnterprisePausesResponse;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
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
        CreateEnterprisePausesRequest request = new CreateEnterprisePausesRequest();
        request.setIsDefault(0);
        request.setIsRest(1);
        request.setPauseStatus("opt");
        CreateEnterprisePausesResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseModel));
    }

    @Test
    public void updateTest() throws ServerException, ClientException, JsonProcessingException {
        UpdateEnterprisePausesRequest request = new UpdateEnterprisePausesRequest();
        request.setId(68068);
        request.setPauseStatus("000");
        request.setIsRest(0);
        request.setIsDefault(0);
        UpdateEnterprisePausesResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseModel));
    }

    @Test
    public void delTest() throws ServerException, ClientException, JsonProcessingException {
        DeleteEnterprisePausesRequest request = new DeleteEnterprisePausesRequest();
        request.setPauseStatus("opt");
        DeleteEnterprisePausesResponse responseModel = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseModel));
    }
}
