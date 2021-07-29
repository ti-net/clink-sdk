package com.tinet.clink.openapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinet.clink.openapi.exceptions.ClientException;
import com.tinet.clink.openapi.exceptions.ServerException;
import com.tinet.clink.openapi.request.monitor.AgentStatusRequest;
import com.tinet.clink.openapi.response.monitor.AgentStatusResponse;
import org.junit.Test;

public class AgentStatusTest extends AbstractTest {

    @Test
    public void testAgentStatus() throws ServerException, ClientException, JsonProcessingException {

        AgentStatusRequest request = new AgentStatusRequest();
        request.setQnos(new String[]{"6666", "0000"});
        // request.setCnos(new String[]{"6666", "6665"});
        /// request.setPauseTypes(new Integer[]{1, 2});
        request.setAgentStatus(new String[]{"PAUSE", "IDLE"});
        request.setAgentStatusDetails(new String[]{"WC","666"});


        AgentStatusResponse response = client.getResponseModel(request);

        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

    }

}
