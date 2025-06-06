package com.tinet.clink.cc.response.stat;

import com.tinet.clink.core.response.PagedResponse;

import java.util.List;
import java.util.Map;

/**
 * @author Chenjf
 * @date 2020/2/24 15:32
 **/
public class StatPreviewObResponse extends PagedResponse {

    private List<Map<String,Object>> statPreviewOb;

    public List<Map<String, Object>> getStatPreviewOb() {
        return statPreviewOb;
    }

    public void setStatPreviewOb(List<Map<String, Object>> statPreviewOb) {
        this.statPreviewOb = statPreviewOb;
    }
}
