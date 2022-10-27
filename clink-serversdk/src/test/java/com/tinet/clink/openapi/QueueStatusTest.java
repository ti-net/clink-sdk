package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.cc.request.monitor.QueueStatusRequest;
import com.tinet.clink.cc.response.monitor.QueueStatusResponse;
import com.tinet.clink.core.exceptions.ClientException;
import com.tinet.clink.core.exceptions.ServerException;


import org.junit.Test;

public class QueueStatusTest extends AbstractTest {

    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void testQueueStatus() throws ServerException, ClientException, JsonProcessingException {

        QueueStatusRequest request = new QueueStatusRequest();
        request.setQnos(new String[]{"6666", "0000"});

        QueueStatusResponse response = client.getResponseModel(request);

        System.out.println(mapper.writeValueAsString(response));

    }

}
