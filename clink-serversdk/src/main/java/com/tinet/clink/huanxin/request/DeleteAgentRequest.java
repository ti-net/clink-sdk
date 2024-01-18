package com.tinet.clink.huanxin.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.huanxin.PathEnum;
import com.tinet.clink.huanxin.response.CreateAgentResponse;
import com.tinet.clink.huanxin.response.DeleteAgentResponse;

public class DeleteAgentRequest extends AbstractRequestModel<DeleteAgentResponse> {

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
