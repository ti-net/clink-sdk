package com.tinet.clink.cc.response.log;



import com.tinet.clink.cc.model.AgentLogModel;
import com.tinet.clink.core.response.PagedResponse;

import java.util.List;

/**
 * 查询座席工作日志列表响应
 *
 * @author wangli
 * @date 2022-03-09 6:10 PM
 */
public class ListAgentLogsResponse extends PagedResponse {

    /**
     * 座席工作日志列表
     */
    List<AgentLogModel> agentLogs;

    public List<AgentLogModel> getAgentLogs() {
        return agentLogs;
    }

    public void setAgentLogs(List<AgentLogModel> agentLogs) {
        this.agentLogs = agentLogs;
    }

}
