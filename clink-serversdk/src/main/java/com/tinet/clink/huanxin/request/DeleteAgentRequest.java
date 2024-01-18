package com.tinet.clink.huanxin.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.huanxin.PathEnum;
import com.tinet.clink.huanxin.response.CreateAgentResponse;
import com.tinet.clink.huanxin.response.DeleteAgentResponse;

/**
 * 删除坐席
 *
 * @author tian.jie
 * @date 2024-01-18 11:33
 */
public class DeleteAgentRequest extends AbstractRequestModel<DeleteAgentResponse> {

    /**
     * 用户名
     */
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public DeleteAgentRequest() {
        super(PathEnum.DELETE_AGENT.value(), HttpMethodType.POST);
    }

    @Override
    public Class<DeleteAgentResponse> getResponseClass() {
        return DeleteAgentResponse.class;
    }
}
