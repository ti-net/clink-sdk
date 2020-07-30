package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.model.ChatMessage;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * @author Wangyl
 * @date 2020/7/29
 */
public class ChatMessageResponse extends PagedResponse {
    List<ChatMessage> chatMessages;

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    /**
     * 游标 id
     */
    private String scrollId;
    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }
}
