package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.UpdateChatHttpPushStatusResponse;

/**
 * 启停数据推送。0 停用，1 启用。
 */
public class UpdateChatHttpPushStatusRequest extends AbstractStatRequest<UpdateChatHttpPushStatusResponse> {

    private Integer id;

    private Integer status;

    public UpdateChatHttpPushStatusRequest() {
        super(PathEnum.UpdateChatHttpPushStatus.value(), HttpMethodType.POST);
    }

    @Override
    public Class<UpdateChatHttpPushStatusResponse> getResponseClass() {
        return UpdateChatHttpPushStatusResponse.class;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
