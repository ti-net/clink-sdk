package com.tinet.clink.kb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * @author zhangpc
 * @since 2024/05/29
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatConversationResponseModel {

    /**
     * 会话ID
     */
    private String conversationId;

    /**
     * 会话内容
     */
    private String title;

    /**
     * 会话类型
     */
    private String contentType;

    /**
     * 对话创建时间
     */
    private Date createTime;

    /**
     * 对话修改时间
     */
    private Date updateTime;

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
