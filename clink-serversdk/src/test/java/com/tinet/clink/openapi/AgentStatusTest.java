package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.monitor.AgentStatusRequest;
import com.tinet.clink.openapi.response.monitor.AgentStatusResponse;
import org.junit.Test;

public class AgentStatusTest extends AbstractTest {

    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void testAgentStatus() throws ServerException, ClientException, JsonProcessingException {

        AgentStatusRequest request = new AgentStatusRequest();

        AgentStatusResponse response = client.getResponseModel(request);

        System.out.println(mapper.writeValueAsString(response.getAgentStatus()));

    }

}
