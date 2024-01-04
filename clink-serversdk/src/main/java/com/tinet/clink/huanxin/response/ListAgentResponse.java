package com.tinet.clink.huanxin.response;

import com.tinet.clink.core.response.PagedResponse;
import com.tinet.clink.huanxin.model.ListAgentModel;

import java.util.List;

public class ListAgentResponse extends PagedResponse {

    private List<ListAgentModel> agents;

    public List<ListAgentModel> getAgents() {
        return agents;
    }

    public void setAgents(List<ListAgentModel> agents) {
        this.agents = agents;
    }
}
