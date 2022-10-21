package com.tinet.clink.cc.response.stat;

import com.tinet.clink.core.response.PagedResponse;

import java.util.List;
import java.util.Map;

/**
 * @author Chenjf
 * @date 2020/2/24 15:32
 **/
public class StatClientStatusResponse extends PagedResponse {

    private List<Map<String,Object>> statClientStatus;

    public List<Map<String, Object>> getStatClientStatus() {
        return statClientStatus;
    }

    public void setStatClientStatus(List<Map<String, Object>> statClientStatus) {
        this.statClientStatus = statClientStatus;
    }
}
