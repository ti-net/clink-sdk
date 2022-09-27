package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.model.ChatInvestigation;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * @author Wangyl
 * @date 2020/7/29
 */
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  ChatInvestigationResponse extends ResponseModel {

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
