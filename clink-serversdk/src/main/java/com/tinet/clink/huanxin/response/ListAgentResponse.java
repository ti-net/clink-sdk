package com.tinet.clink.huanxin.response;

import com.tinet.clink.core.response.PagedResponse;
import com.tinet.clink.huanxin.model.AgentModel;

import java.util.List;

public class ListAgentResponse extends PagedResponse {

    private List<AgentModel> agents;

    public List<AgentModel> getAgents() {
        return agents;
    }

    public void setAgents(List<AgentModel> agents) {
        this.agents = agents;
    }
}
