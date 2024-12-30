package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;

import java.util.Map;

/**
 * 在线客服 - 座席考勤报表（新）
 *
 * @author wangbin
 * @since 2024/12/10
 */
public class ClientStatAttendancePageResponse extends ResponseModel {

    private Map<String, Object> data;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
