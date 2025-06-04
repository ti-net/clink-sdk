package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;

import java.util.Map;

/**
 * 在线客服 - 同步座席、机器人会话记录（新）
 *
 * @author wangbin
 * @since 2024/12/10
 */
public class SessionDetailRecordResponse extends ResponseModel {

    private Map<String, Object> data;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
