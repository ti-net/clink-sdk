package com.tinet.clink.cc.response.exten;

import com.tinet.clink.openapi.model.DescribeExtenResultModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 查询话机详情响应
 *
 * @author wangyq
 * @date 2018/10/26
 */
public class DescribeExtenResponse extends ResponseModel {

    /**
     * 话机详情对象
     */
    private DescribeExtenResultModel exten;

    public DescribeExtenResultModel getExten() {
        return exten;
    }

    public void setExten(DescribeExtenResultModel exten) {
        this.exten = exten;
    }
}