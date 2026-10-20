package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.livechat.model.ChatHttpPushPageModel;

/**
 * 分页查询数据推送响应
 */
public class ListChatHttpPushResponse extends ResponseModel {

    private ChatHttpPushPageModel page;

    public ChatHttpPushPageModel getPage() {
        return page;
    }

    public void setPage(ChatHttpPushPageModel page) {
        this.page = page;
    }
}
