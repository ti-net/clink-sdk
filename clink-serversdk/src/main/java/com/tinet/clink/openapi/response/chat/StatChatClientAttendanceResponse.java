package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.response.ResponseModel;
import java.util.List;
import java.util.Map;

/**
 * 在线客服-座席工作量报表 Response
 */
public class StatChatClientAttendanceResponse extends ResponseModel {

    private List<Map<String, Object>> statChatClientAttendance;

    public List<Map<String, Object>> getStatChatClientAttendance() {
        return statChatClientAttendance;
    }

    public void setStatChatClientAttendance(List<Map<String, Object>> statChatClientAttendance) {
        this.statChatClientAttendance = statChatClientAttendance;
    }
}
