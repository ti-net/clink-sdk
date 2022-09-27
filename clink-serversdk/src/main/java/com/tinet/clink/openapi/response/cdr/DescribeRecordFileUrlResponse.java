package com.tinet.clink.openapi.response.cdr;

import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 查询通话录音地址响应
 *
 * @author Wangyl
 * @date 2019/7/9
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DescribeRecordFileUrlResponse extends ResponseModel {
    private String recordFileUrl;

    public String getRecordFileUrl() {
        return recordFileUrl;
    }

    public void setRecordFileUrl(String recordFileUrl) {
        this.recordFileUrl = recordFileUrl;
    }
}
