package com.tinet.clink.openapi.response.log;

import com.tinet.clink.openapi.model.AgentLogModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 查询座席工作日志列表响应
 *
 * @author wangli
 * @date 2022-03-09 6:10 PM
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListAgentLogsResponse extends PagedResponse {

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
