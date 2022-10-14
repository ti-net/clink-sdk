package com.tinet.clink.openapi.response.config.exten;

import com.tinet.clink.openapi.model.DescribeExtenResultModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 查询话机详情响应
 *
 * @author wangyq
 * @date 2018/10/26
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DescribeExtenResponse extends ResponseModel {

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