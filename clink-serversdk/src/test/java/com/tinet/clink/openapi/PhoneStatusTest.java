package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.tinet.clink.cc.request.added.PhoneStatusRequest;
import com.tinet.clink.cc.response.added.PhoneStatusResponse;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;
import org.junit.Test;

public class PhoneStatusTest extends AbstractTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void check() throws ServerException, ClientException, JsonProcessingException {

        PhoneStatusRequest request = new PhoneStatusRequest();
        request.setTel("13716619459");

        PhoneStatusResponse response = client.getResponseModel(request);

        System.out.println(mapper.writeValueAsString(response));
    }
}
