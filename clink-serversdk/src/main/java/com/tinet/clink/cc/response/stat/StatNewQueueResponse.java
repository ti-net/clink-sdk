package com.tinet.clink.cc.response.stat;

import com.tinet.clink.core.response.PagedResponse;

import java.util.List;
import java.util.Map;

/**
 * @author yuqiang
 * @date 2023/9/11
 **/
public class StatNewQueueResponse extends PagedResponse {

    private List<Map<String,Object>> statNewQueue;

    public List<Map<String, Object>> getStatNewQueue() {
        return statNewQueue;
    }

    public void setStatNewQueue(List<Map<String, Object>> statNewQueue) {
        this.statNewQueue = statNewQueue;
    }
}
