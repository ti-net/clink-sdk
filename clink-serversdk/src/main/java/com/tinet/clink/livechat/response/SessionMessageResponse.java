package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;

import java.util.List;
import java.util.Map;

/**
 * 在线客服 - 查询聊天记录（新）
 *
 * @author wangbin
 * @since 2024/12/10
 */
public class SessionMessageResponse extends ResponseModel {

    private List<Map<String, Object>> data;

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }
}
