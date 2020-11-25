package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;
import java.util.Map;

/**
 * 在线客服-座席工作量报表 Response
 *
 * @author ningkun
 * @date 2020/11/25
 */
public class StatChatClientWorkloadResponse extends ResponseModel {

    private List<Map<String, Object>> statChatClientWorkload;

    public List<Map<String, Object>> getStatChatClientWorkload() {
        return statChatClientWorkload;
    }

    public void setStatChatClientWorkload(List<Map<String, Object>> statChatClientWorkload) {
        this.statChatClientWorkload = statChatClientWorkload;
    }
}
