package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.response.PagedResponse;

/**
 * @author midong
 * @date 2022/6/8 15:52
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ChatVisitorInfoResponse extends PagedResponse {


    Long unreadCount;

    public Long getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Long unreadCount) {
        this.unreadCount = unreadCount;
    }


}
