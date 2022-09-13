package com.tinet.clink.openapi.stat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.cc.request.stat.StatCallIbAreaRequest;
import com.tinet.clink.cc.response.stat.StatCallIbAreaResponse;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.openapi.AbstractTest;
import com.tinet.clink.core.exceptions.ServerException;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StatCallIbAreaTest extends AbstractTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void statCallIbArea() throws ServerException, ClientException, JsonProcessingException {

        StatCallIbAreaRequest request = new StatCallIbAreaRequest();
        request.setDate("20220524");
        List list = new ArrayList<>();
        list.add("province");
        request.setFields(list);
        StatCallIbAreaResponse response = client.getResponseModel(request);
        System.out.println(mapper.writeValueAsString(response.getStatCallIbArea()));
    }
}
