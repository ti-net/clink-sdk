package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;
import java.util.Map;

/**
 * 在线客服-满意度报表 Response
 *
 * @author midong
 * @date 2024/11/11
 */
public class StatInvestigationCnoPageResponse extends ResponseModel {

    private Map<String, Object> data;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
