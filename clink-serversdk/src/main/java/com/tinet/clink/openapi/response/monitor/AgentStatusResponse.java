package com.tinet.clink.openapi.response.monitor;

import com.tinet.clink.openapi.model.AgentStatusModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * 查询座席状态响应对象
 *
 * @author wangll
 * @date 2019/09/11
 **/
public class AgentStatusResponse extends ResponseModel {

    private List<AgentStatusModel> agentStatus;


    public List<AgentStatusModel> getAgentStatus() {
        return agentStatus;
    }

    public void setAgentStatus(List<AgentStatusModel> agentStatus) {
        this.agentStatus = agentStatus;
    }
}
