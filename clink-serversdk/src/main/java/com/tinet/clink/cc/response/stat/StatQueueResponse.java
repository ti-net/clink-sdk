package com.tinet.clink.cc.response.stat;

import com.tinet.clink.core.response.PagedResponse;

import java.util.List;
import java.util.Map;

/**
 * @author Chenjf
 * @date 2020/2/24 15:32
 **/
public class StatQueueResponse extends PagedResponse {

    private List<Map<String,Object>> statQueue;

    public List<Map<String, Object>> getStatQueue() {
        return statQueue;
    }

    public void setStatQueue(List<Map<String, Object>> statQueue) {
        this.statQueue = statQueue;
    }
}
