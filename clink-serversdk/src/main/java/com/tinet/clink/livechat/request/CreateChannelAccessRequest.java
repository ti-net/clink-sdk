package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.CreateChannelAccessResponse;

/**
 * 创建渠道接入号
 */
public class CreateChannelAccessRequest extends AbstractStatRequest<CreateChannelAccessResponse> {

    private Integer contactType;
    private String accessId;
    private String name;
    private Integer status;
    private Integer ivrId;
    /**
     * 渠道特有字段
     */
    private Object extension;

    public CreateChannelAccessRequest() {
        super(PathEnum.CreateChannelAccess.value(), HttpMethodType.POST);
    }

    @Override
    public Class<CreateChannelAccessResponse> getResponseClass() {
        return CreateChannelAccessResponse.class;
    }

    public Integer getContactType() {
        return contactType;
    }

    public void setContactType(Integer contactType) {
        this.contactType = contactType;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIvrId() {
        return ivrId;
    }

    public void setIvrId(Integer ivrId) {
        this.ivrId = ivrId;
    }

    public Object getExtension() {
        return extension;
    }

    public void setExtension(Object extension) {
        this.extension = extension;
    }
}
