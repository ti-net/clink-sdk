package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.model.ChatRecord;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * @author Wangyl
 * @date 2020/7/29
 */
public class ChatRecordResponse extends ResponseModel {

    List<ChatRecord> records;

    /**
     * 游标 id
     */
    private String scrollId;

    public List<ChatRecord> getRecords() {
        return records;
    }

    public void setRecords(List<ChatRecord> records) {
        this.records = records;
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }
}
