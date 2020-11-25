package com.tinet.clink.openapi.response.chat;

import java.util.List;
import java.util.Map;

/**
 * 在线客服-队列报表数据
 *
 * @author ningkun
 * @date 2020/11/25
 */
public class StatChatQueueResponseModel {

    List<Map<String, Object>> data;

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }
}
