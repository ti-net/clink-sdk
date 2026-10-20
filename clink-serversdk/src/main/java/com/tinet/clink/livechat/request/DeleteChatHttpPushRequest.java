package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.DeleteChatHttpPushResponse;

/**
 * 删除数据推送
 */
public class DeleteChatHttpPushRequest extends AbstractStatRequest<DeleteChatHttpPushResponse> {

    /**
     * 推送配置 ID
     */
    private Integer id;

    public DeleteChatHttpPushRequest() {
        super(PathEnum.DeleteChatHttpPush.value(), HttpMethodType.POST);
    }

    @Override
    public Class<DeleteChatHttpPushResponse> getResponseClass() {
        return DeleteChatHttpPushResponse.class;
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
