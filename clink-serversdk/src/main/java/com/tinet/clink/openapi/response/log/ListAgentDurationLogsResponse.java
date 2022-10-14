package com.tinet.clink.openapi.response.log;

import com.tinet.clink.openapi.model.AgentDurationLogModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 查询座席工作时长日志列表响应
 *
 * @author wangli
 * @date 2022-03-10 5:30 PM
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListAgentDurationLogsResponse extends PagedResponse {

    /**
     * 座席工作时长日志列表
     */
    List<AgentDurationLogModel> agentDurationLogs;

    public List<AgentDurationLogModel> getAgentDurationLogs() {
        return agentDurationLogs;
    }

    public void setAgentDurationLogs(List<AgentDurationLogModel> agentDurationLogs) {
        this.agentDurationLogs = agentDurationLogs;
    }

}
