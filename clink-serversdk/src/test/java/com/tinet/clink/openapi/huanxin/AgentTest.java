package com.tinet.clink.openapi.huanxin;

import com.tinet.clink.huanxin.request.CreateAgentRequest;
import com.tinet.clink.huanxin.request.DeleteAgentRequest;
import com.tinet.clink.huanxin.request.ListAgentRequest;
import com.tinet.clink.huanxin.request.UpdateAgentRequest;
import com.tinet.clink.huanxin.response.CreateAgentResponse;
import com.tinet.clink.huanxin.response.DeleteAgentResponse;
import com.tinet.clink.huanxin.response.ListAgentResponse;
import com.tinet.clink.huanxin.response.UpdateAgentResponse;
import com.tinet.clink.openapi.AbstractTest;
import org.junit.Test;

public class AgentTest extends AbstractTest {


    @Test
    public void createAgent() throws Exception {
        CreateAgentRequest createAgentRequest = new CreateAgentRequest();
        createAgentRequest.setNicename("tj06");
        createAgentRequest.setUsername("tianjie6@ti-net.com.cn");
        createAgentRequest.setPassword("123456");
        createAgentRequest.setTrueName("田杰");
        createAgentRequest.setMobilePhone("15251718061");
        createAgentRequest.setAgentNumber(1009);
        createAgentRequest.setRoleId(1);
        createAgentRequest.setAgentType("All");
        createAgentRequest.setMaxServiceSessionCount(10);
        CreateAgentResponse response = client.getResponseModel(createAgentRequest);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

    }

    @Test
    public void deleteAgent() throws Exception {
        DeleteAgentRequest deleteAgentRequest = new DeleteAgentRequest();
        deleteAgentRequest.setUsername("tianjie3@ti-net.com.cn");
        DeleteAgentResponse response = client.getResponseModel(deleteAgentRequest);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
    }

    @Test
    public void updateAgent() throws Exception {
        UpdateAgentRequest request = new UpdateAgentRequest();
        request.setNicename("tj01");
        request.setUsername("tianjie1@ti-net.com.cn");
        request.setTrueName("田杰");
        request.setMobilePhone("15251718061");
        request.setRoleId(1);
        request.setAgentType("All");
        request.setMaxServiceSessionCount(10);
        UpdateAgentResponse response = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

    }

    @Test
    public void listAgent() throws Exception {
        ListAgentRequest request = new ListAgentRequest();
        request.setPage(0);
        request.setSize(10);
        request.setAgentType("All");
        ListAgentResponse response = client.getResponseModel(request);
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));

    }
}
