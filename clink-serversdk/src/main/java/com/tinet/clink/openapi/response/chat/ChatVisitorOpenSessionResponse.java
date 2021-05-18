package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author wangpw
 * @date 2021年5月14日
 */
public class ChatVisitorOpenSessionResponse extends ResponseModel {

    /**
     * 会话id
     */
    private String sessionId;

    /**
     * 会话开始时间（毫秒）
     */
    private Long startTime;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }
}
