package com.tinet.clink.openapi.response.stat;

import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;
import java.util.Map;

/**
 * @author Chenjf
 * @date 2020/2/24 15:32
 **/
public class StatClientWorkloadResponse extends PagedResponse {

    private List<Map<String,Object>> statClientWorkload;

    public List<Map<String, Object>> getStatClientWorkload() {
        return statClientWorkload;
    }

    public void setStatClientWorkload(List<Map<String, Object>> statClientWorkload) {
        this.statClientWorkload = statClientWorkload;
    }
}
