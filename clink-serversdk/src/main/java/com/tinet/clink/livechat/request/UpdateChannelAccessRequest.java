package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.UpdateChannelAccessResponse;

/**
 * 更新渠道接入号、绑定导航或一块会话配置
 */
public class UpdateChannelAccessRequest extends AbstractStatRequest<UpdateChannelAccessResponse> {

    private Integer contactType;
    private String accessId;
    private String name;
    private Integer status;
    private Integer ivrId;
    /**
     * base、queue、dispatch、bridge、end、timeout、investigation 之一
     */
    private String block;
    /**
     * 与 block 对应的配置内容
     */
    private Object setting;
    /**
     * 渠道特有字段补丁
     */
    private Object extension;

    public UpdateChannelAccessRequest() {
        super(PathEnum.UpdateChannelAccess.value(), HttpMethodType.POST);
    }

    @Override
    public Class<UpdateChannelAccessResponse> getResponseClass() {
        return UpdateChannelAccessResponse.class;
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

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public Object getSetting() {
        return setting;
    }

    public void setSetting(Object setting) {
        this.setting = setting;
    }

    public Object getExtension() {
        return extension;
    }

    public void setExtension(Object extension) {
        this.extension = extension;
    }
}
