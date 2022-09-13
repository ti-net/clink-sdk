package com.tinet.clink.cc.response.monitor;

import com.tinet.clink.cc.model.AgentStatusDetailModel;
import com.tinet.clink.core.response.ResponseModel;

/**
 * 查询座席状态详情响应对象
 *
 * @author yinzk
 * @date 2022/2/9
 **/
public class AgentStatusDetailResponse extends ResponseModel {

    private AgentStatusDetailModel agentStatusDetail;

    public AgentStatusDetailModel getAgentStatusDetail() {
        return agentStatusDetail;
    }

    public void setAgentStatusDetail(AgentStatusDetailModel agentStatusDetail) {
        this.agentStatusDetail = agentStatusDetail;
    }

}
