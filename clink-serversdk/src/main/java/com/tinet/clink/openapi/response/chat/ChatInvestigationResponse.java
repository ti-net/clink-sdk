package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.model.CdrIbRecordModel;
import com.tinet.clink.openapi.model.ChatInvestigation;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * @author Wangyl
 * @date 2020/7/29
 */
public class ChatInvestigationResponse extends PagedResponse {

    List<ChatInvestigation> chatInvestigations;
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

    public List<ChatInvestigation> getChatInvestigations() {
        return chatInvestigations;
    }

    public void setChatInvestigations(List<ChatInvestigation> chatInvestigations) {
        this.chatInvestigations = chatInvestigations;
    }
}
