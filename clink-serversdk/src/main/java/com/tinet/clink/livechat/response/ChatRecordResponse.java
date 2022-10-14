package com.tinet.clink.livechat.response;

import com.tinet.clink.livechat.model.ChatRecord;
import com.tinet.clink.core.response.ResponseModel;

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
