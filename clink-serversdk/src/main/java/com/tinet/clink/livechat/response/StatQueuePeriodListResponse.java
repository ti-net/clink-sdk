package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;
import java.util.List;
import java.util.Map;

/**
 * 在线客服-队列报表_按时间段查询 Response
 *
 * @author midong
 * @date 2024/11/11
 */
public class StatQueuePeriodListResponse extends ResponseModel {

    private List<Map<String, Object>> data;

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

}
