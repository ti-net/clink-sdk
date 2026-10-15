package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.response.DeleteChannelAccessResponse;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;

/**
 * 删除渠道接入号
 */
public class DeleteChannelAccessRequest extends AbstractStatRequest<DeleteChannelAccessResponse> {

    /**
     * 渠道类型
     */
    private Integer contactType;

    /**
     * 接入号
     */
    private String accessId;

    public DeleteChannelAccessRequest() {
        super(PathEnum.DeleteChannelAccess.value(), HttpMethodType.POST);
    }

    @Override
    public Class<DeleteChannelAccessResponse> getResponseClass() {
        return DeleteChannelAccessResponse.class;
    }

    public Integer getContactType() {
        return contactType;
    }

    public void setContactType(Integer contactType) {
        this.contactType = contactType;
        if (contactType != null) {
            putQueryParameter("contactType", contactType);
        }
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
        if (accessId != null) {
            putQueryParameter("accessId", accessId);
        }
    }

}
