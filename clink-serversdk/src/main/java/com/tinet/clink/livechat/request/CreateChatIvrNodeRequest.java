package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.CreateChatIvrNodeResponse;

/**
 * 新增路由导航节点
 */
public class CreateChatIvrNodeRequest extends AbstractStatRequest<CreateChatIvrNodeResponse> {

    private Integer ivrId;
    private String endpoint;
    private String name;
    private Integer type;
    /**
     * 节点属性，结构与网页端相同
     */
    private Object property;

    public CreateChatIvrNodeRequest() {
        super(PathEnum.CreateChatIvrNode.value(), HttpMethodType.POST);
    }

    @Override
    public Class<CreateChatIvrNodeResponse> getResponseClass() {
        return CreateChatIvrNodeResponse.class;
    }

    public Integer getIvrId() {
        return ivrId;
    }

    public void setIvrId(Integer ivrId) {
        this.ivrId = ivrId;
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
}
