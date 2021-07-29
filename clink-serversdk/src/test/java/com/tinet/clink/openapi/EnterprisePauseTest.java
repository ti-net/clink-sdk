package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.config.enterprise.pause.ListEnterprisePausesRequest;
import com.tinet.clink.openapi.response.config.enterprise.pause.ListEnterprisePausesResponse;
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
}
