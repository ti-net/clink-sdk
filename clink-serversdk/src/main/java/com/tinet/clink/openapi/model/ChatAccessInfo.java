package com.tinet.clink.openapi.model;


/**
 * \* Created with IntelliJ IDEA.
 * \* @author: sunqian
 * \* @date: 2022/5/27
 * \* @time: 16:54
 * \* @description:
 * \
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ChatAccessInfo {

    /**
     * 接入号ID
     */
    private String accessId = "";

    /**
     * 接入号最后一条消息
     */
    private String lastMessage = "";

    /**
     * 接入号未读消息数
     */
    private Long unreadCount = 0L;

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Long getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Long unreadCount) {
        this.unreadCount = unreadCount;
    }
}
