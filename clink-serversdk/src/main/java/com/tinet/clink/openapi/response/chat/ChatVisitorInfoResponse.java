package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.response.PagedResponse;

/**
 * @author midong
 * @date 2022/6/8 15:52
 */
public class ChatVisitorInfoResponse extends PagedResponse {


    Long unreadCount;

    public Long getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Long unreadCount) {
        this.unreadCount = unreadCount;
    }


}
