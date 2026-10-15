package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.UpdateChatIvrNodeResponse;

/**
 * 修改路由导航节点，可选同时提交画布
 */
public class UpdateChatIvrNodeRequest extends AbstractStatRequest<UpdateChatIvrNodeResponse> {

    private Integer ivrId;
    private Integer nodeId;
    private String endpoint;
    private String name;
    private Integer type;
    /**
     * 不传则不改属性
     */
    private Object property;
    /**
     * 不传则不改画布；传入时随节点一起提交
     */
    private Object flow;

    public UpdateChatIvrNodeRequest() {
        super(PathEnum.UpdateChatIvrNode.value(), HttpMethodType.POST);
    }

    @Override
    public Class<UpdateChatIvrNodeResponse> getResponseClass() {
        return UpdateChatIvrNodeResponse.class;
    }

    public Integer getIvrId() {
        return ivrId;
    }

    public void setIvrId(Integer ivrId) {
        this.ivrId = ivrId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getProperty() {
        return property;
    }

    public void setProperty(Object property) {
        this.property = property;
    }

    public Object getFlow() {
        return flow;
    }

    public void setFlow(Object flow) {
        this.flow = flow;
    }
}
