package com.tinet.clink.cc.response.monitor;

import com.tinet.clink.cc.model.AgentStatusModel;
import com.tinet.clink.core.response.PagedResponse;

import java.util.List;

/**
 * 查询座席状态响应对象
 *
 * @author wangll
 * @date 2019/09/11
 **/
public class AgentStatusResponse extends PagedResponse {

    private List<AgentStatusModel> agentStatus;


    public List<AgentStatusModel> getAgentStatus() {
        return agentStatus;
    }

    public void setAgentStatus(List<AgentStatusModel> agentStatus) {
        this.agentStatus = agentStatus;
    }
}
