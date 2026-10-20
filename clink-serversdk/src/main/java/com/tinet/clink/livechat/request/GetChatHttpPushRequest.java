package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.GetChatHttpPushResponse;

/**
 * 查询数据推送详情
 */
public class GetChatHttpPushRequest extends AbstractStatRequest<GetChatHttpPushResponse> {

    /**
     * 推送配置 ID
     */
    private Integer id;

    public GetChatHttpPushRequest() {
        super(PathEnum.GetChatHttpPush.value(), HttpMethodType.POST);
    }

    @Override
    public Class<GetChatHttpPushResponse> getResponseClass() {
        return GetChatHttpPushResponse.class;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if (id != null) {
            putQueryParameter("id", id);
        }
    }
}
