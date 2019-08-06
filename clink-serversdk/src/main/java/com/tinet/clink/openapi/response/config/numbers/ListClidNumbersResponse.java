package com.tinet.clink.openapi.response.config.numbers;

import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * 查询外显号码集合响应
 *
 * @author wangyq
 * @date 2018/10/26
 */
public class ListClidNumbersResponse extends ResponseModel {

    /**
     * 外显号码集合
     */
    private List<String> clids;

    public List<String> getClids() {
        return clids;
    }

    public void setClids(List<String> clids) {
        this.clids = clids;
    }
}