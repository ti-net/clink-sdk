package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.livechat.model.ChatHttpPushModel;

/**
 * 查询数据推送详情响应
 */
public class GetChatHttpPushResponse extends ResponseModel {

    private ChatHttpPushModel push;

    public ChatHttpPushModel getPush() {
        return push;
    }

    public void setPush(ChatHttpPushModel push) {
        this.push = push;
    }
}
