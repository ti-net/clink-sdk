package com.tinet.clink.openapi.response.stat;

import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;
import java.util.Map;

/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  StatCallIbAreaResponse extends PagedResponse {

    private List<Map<String,Object>> statCallIbArea;

    public List<Map<String, Object>> getStatCallIbArea() {
        return statCallIbArea;
    }

    public void setStatCallIbArea(List<Map<String, Object>> statCallIbArea) {
        this.statCallIbArea = statCallIbArea;
    }
}
