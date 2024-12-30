package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;

import java.util.Map;
/**
 * 在线客服 - 同步会话记录（新）
 *
 * @author wangbin
 * @since 2024/12/10
 */
public class SessionRecordResponse extends ResponseModel {

    private Map<String, Object> data;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
