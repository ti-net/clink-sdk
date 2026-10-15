package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.response.GetChatIvrNodeResponse;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;

/**
 * 按出口查询导航节点
 */
public class GetChatIvrNodeRequest extends AbstractStatRequest<GetChatIvrNodeResponse> {

    /**
     * 导航ID
     */
    private Integer ivrId;

    /**
     * 节点出口
     */
    private String endpoint;

    public GetChatIvrNodeRequest() {
        super(PathEnum.GetChatIvrNode.value(), HttpMethodType.POST);
    }

    @Override
    public Class<GetChatIvrNodeResponse> getResponseClass() {
        return GetChatIvrNodeResponse.class;
    }

    public Integer getIvrId() {
        return ivrId;
    }

    public void setIvrId(Integer ivrId) {
        this.ivrId = ivrId;
        if (ivrId != null) {
            putQueryParameter("ivrId", ivrId);
        }
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        if (endpoint != null) {
            putQueryParameter("endpoint", endpoint);
        }
    }

}
