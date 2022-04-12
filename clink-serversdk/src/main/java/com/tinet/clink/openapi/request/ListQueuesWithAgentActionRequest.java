package com.tinet.clink.openapi.request;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.response.ListQueuesWithAgentActionResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

public class ListQueuesWithAgentActionRequest extends AbstractRequestModel<ListQueuesWithAgentActionResponse> {

    /**
     * 座席号
     */
    private String cno;

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putQueryParameter("cno", cno);
        }
    }

    public ListQueuesWithAgentActionRequest() {
        super(PathEnum.ListQueuesWithAgentAction.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListQueuesWithAgentActionResponse> getResponseClass() {
        return ListQueuesWithAgentActionResponse.class;
    }
}
