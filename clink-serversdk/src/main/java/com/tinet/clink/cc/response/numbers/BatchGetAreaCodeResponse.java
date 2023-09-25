package com.tinet.clink.cc.response.numbers;

import com.tinet.clink.cc.model.AreaCodeModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.Map;

public class BatchGetAreaCodeResponse extends ResponseModel {
    private Map<String, AreaCodeModel> data;

    public Map<String, AreaCodeModel> getData() {
        return data;
    }

    public void setData(Map<String, AreaCodeModel> areaCodeMap) {
        this.data = areaCodeMap;
    }
}
