package com.tinet.clink.livechat.response;

import com.tinet.clink.livechat.model.ChatInvestigation;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * @author Wangyl
 * @date 2020/7/29
 */
public class ChatInvestigationResponse extends ResponseModel {

    List<ChatInvestigation> records;
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

    public List<ChatInvestigation> getRecords() {
        return records;
    }

    public void setRecords(List<ChatInvestigation> records) {
        this.records = records;
    }
}
