package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.response.GetChannelAccessResponse;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;

/**
 * 按渠道和 accessId 查询接入号
 */
public class GetChannelAccessRequest extends AbstractStatRequest<GetChannelAccessResponse> {

    /**
     * 渠道类型
     */
    private Integer contactType;

    /**
     * 接入号
     */
    private String accessId;

    public GetChannelAccessRequest() {
        super(PathEnum.GetChannelAccess.value(), HttpMethodType.POST);
    }

    @Override
    public Class<GetChannelAccessResponse> getResponseClass() {
        return GetChannelAccessResponse.class;
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
