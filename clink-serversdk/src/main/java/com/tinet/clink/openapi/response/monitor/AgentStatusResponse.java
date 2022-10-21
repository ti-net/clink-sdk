package com.tinet.clink.openapi.response.monitor;

import com.tinet.clink.openapi.model.AgentStatusModel;
import com.tinet.clink.openapi.response.PagedResponse;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * 查询座席状态响应对象
 *
 * @author wangll
 * @date 2019/09/11
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  AgentStatusResponse extends PagedResponse {

    private List<AgentStatusModel> agentStatus;


    public List<AgentStatusModel> getAgentStatus() {
        return agentStatus;
    }

    public void setAgentStatus(List<AgentStatusModel> agentStatus) {
        this.agentStatus = agentStatus;
    }
}
