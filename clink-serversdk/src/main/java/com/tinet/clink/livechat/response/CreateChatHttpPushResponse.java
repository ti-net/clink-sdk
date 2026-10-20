package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.livechat.model.ChatHttpPushModel;

/**
 * 创建数据推送响应
 */
public class CreateChatHttpPushResponse extends ResponseModel {

    private ChatHttpPushModel push;

    public ChatHttpPushModel getPush() {
        return push;
    }

    public void setPush(ChatHttpPushModel push) {
        this.push = push;
    }
}
