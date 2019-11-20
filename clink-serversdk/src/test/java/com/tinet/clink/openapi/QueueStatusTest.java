package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.monitor.AgentStatusRequest;
import com.tinet.clink.openapi.request.monitor.QueueStatusRequest;
import com.tinet.clink.openapi.response.monitor.AgentStatusResponse;
import com.tinet.clink.openapi.response.monitor.QueueStatusResponse;
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
