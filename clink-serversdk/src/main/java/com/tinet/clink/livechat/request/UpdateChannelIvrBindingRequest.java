package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.UpdateChannelIvrBindingResponse;

/**
 * 修改渠道接入号绑定的路由导航
 */
public class UpdateChannelIvrBindingRequest extends AbstractStatRequest<UpdateChannelIvrBindingResponse> {

    /**
     * 渠道类型，取值同查询接口的 contactType
     */
    private String contactType;

    /**
     * 接入号ID
     */
    private String accessId;

    /**
     * 路由导航ID，大于等于 1，且必须属于本企业
     */
    private Integer ivrId;

    public UpdateChannelIvrBindingRequest() {
        super(PathEnum.UpdateChannelIvrBinding.value(), HttpMethodType.POST);
    }

    @Override
    public Class<UpdateChannelIvrBindingResponse> getResponseClass() {
        return UpdateChannelIvrBindingResponse.class;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public Integer getIvrId() {
        return ivrId;
    }

    public void setIvrId(Integer ivrId) {
        this.ivrId = ivrId;
    }
}
