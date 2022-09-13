package com.tinet.clink.cc.response.stat;

import com.tinet.clink.core.response.PagedResponse;

import java.util.List;
import java.util.Map;

public class StatCallIbAreaResponse extends PagedResponse {

    private List<Map<String,Object>> statCallIbArea;

    public List<Map<String, Object>> getStatCallIbArea() {
        return statCallIbArea;
    }

    public void setStatCallIbArea(List<Map<String, Object>> statCallIbArea) {
        this.statCallIbArea = statCallIbArea;
    }
}
