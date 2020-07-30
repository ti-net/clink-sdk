package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.model.ChatDetail;
import com.tinet.clink.openapi.model.ChatMessage;
import com.tinet.clink.openapi.response.PagedResponse;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * @author Wangyl
 * @date 2020/7/29
 */
public class ChatDetailResponse extends ResponseModel {
    List<ChatDetail> chatDetails;
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
    public List<ChatDetail> getChatDetails() {
        return chatDetails;
    }

    public void setChatDetails(List<ChatDetail> chatDetails) {
        this.chatDetails = chatDetails;
    }
}
