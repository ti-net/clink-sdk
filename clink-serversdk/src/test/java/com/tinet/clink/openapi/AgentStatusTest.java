package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.monitor.AgentStatusRequest;
import com.tinet.clink.openapi.response.monitor.AgentStatusResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AgentStatusTest extends AbstractTest {

    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void testAgentStatus() throws ServerException, ClientException, JsonProcessingException {

        AgentStatusRequest request = new AgentStatusRequest();
        request.setQnos(new String[]{"6666", "0000"});
        request.setCnos(new String[]{"6666", "6665"});
        /// request.setPauseTypes(new Integer[]{1, 2});
        // request.setAgentStatus(new String[]{"PAUSE", "IDLE"});

        AgentStatusResponse response = client.getResponseModel(request);

        System.out.println(mapper.writeValueAsString(response));

    }

}
