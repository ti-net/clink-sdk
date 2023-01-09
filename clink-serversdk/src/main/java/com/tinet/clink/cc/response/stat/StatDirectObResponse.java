package com.tinet.clink.cc.response.stat;

import com.tinet.clink.core.response.ResponseModel;

import java.util.List;
import java.util.Map;

/**
 * 外呼报表-主叫外呼
 * @author libin
 * @date 2023-01-03 11:37
 */
public class StatDirectObResponse  extends ResponseModel {

    private List<Map<String,Object>> statDirectOb;

    public List<Map<String, Object>> getStatDirectOb() {
        return statDirectOb;
    }

    public void setStatDirectOb(List<Map<String, Object>> statDirectOb) {
        this.statDirectOb = statDirectOb;
    }
}
