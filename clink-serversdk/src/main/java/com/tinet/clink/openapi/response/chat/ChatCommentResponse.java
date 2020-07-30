package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.model.ChatComment;
import com.tinet.clink.openapi.model.ChatMessage;
import com.tinet.clink.openapi.response.PagedResponse;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * @author Wangyl
 * @date 2020/7/29
 */
public class ChatCommentResponse extends ResponseModel {
    List<ChatComment> chatComments;
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
    public List<ChatComment> getChatComments() {
        return chatComments;
    }

    public void setChatInfos(List<ChatComment> chatComments) {
        this.chatComments = chatComments;
    }
}
