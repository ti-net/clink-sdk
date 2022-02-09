package com.tinet.clink.openapi.response.monitor;

import com.tinet.clink.openapi.model.AgentStatusModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 查询座席状态详情响应对象
 *
 * @author yinzk
 * @date 2022/2/9
 **/
public class AgentStatusDetailResponse extends ResponseModel {

    private AgentStatusModel agentStatusDetail;

    public AgentStatusModel getAgentStatusDetail() {
        return agentStatusDetail;
    }

    public void setAgentStatusDetail(AgentStatusModel agentStatusDetail) {
        this.agentStatusDetail = agentStatusDetail;
    }

}
