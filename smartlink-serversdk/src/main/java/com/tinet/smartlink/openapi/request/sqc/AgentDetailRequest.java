package com.tinet.smartlink.openapi.request.sqc;

import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.AgentDetailResponse;

import java.util.Set;

/**
 * 推送AsrText文件
 *
 * @author wenjd
 * @date 2019/04/01
 */
public class AgentDetailRequest extends BaseRequest<AgentDetailResponse> {


    /**
     * 用户ID
     */
    private String userId;

    /**
     * 坐席工号集合
     */
    private Set<String> agentNumbers;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        if (userId != null) {
            putBodyParameter("userId", userId);
        }
    }

    public Set<String> getAgentNumbers() {
        return agentNumbers;
    }

    public void setAgentNumbers(Set<String> agentNumbers) {
        this.agentNumbers = agentNumbers;
        if (agentNumbers != null) {
            putBodyParameter("agentNumbers", agentNumbers);
        }
    }

    public AgentDetailRequest() {
        super("/sqc/agentDetail", HttpMethodType.POST);
    }

    @Override
    public Class<AgentDetailResponse> getResponseClass() {
        return AgentDetailResponse.class;
    }
}
