package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.UpdateChatIvrFlowResponse;

/**
 * 保存整张路由导航画布
 */
public class UpdateChatIvrFlowRequest extends AbstractStatRequest<UpdateChatIvrFlowResponse> {

    private Integer ivrId;
    /**
     * 整张画布，结构与网页端保存的 flow 相同
     */
    private Object flow;

    public UpdateChatIvrFlowRequest() {
        super(PathEnum.UpdateChatIvrFlow.value(), HttpMethodType.POST);
    }

    @Override
    public Class<UpdateChatIvrFlowResponse> getResponseClass() {
        return UpdateChatIvrFlowResponse.class;
    }

    public Integer getIvrId() {
        return ivrId;
    }

    public void setIvrId(Integer ivrId) {
        this.ivrId = ivrId;
    }

    public Object getFlow() {
        return flow;
    }

    public void setFlow(Object flow) {
        this.flow = flow;
    }
}
