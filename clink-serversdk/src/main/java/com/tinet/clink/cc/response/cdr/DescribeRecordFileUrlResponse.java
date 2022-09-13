package com.tinet.clink.cc.response.cdr;

import com.tinet.clink.core.response.ResponseModel;

/**
 * 查询通话录音地址响应
 *
 * @author Wangyl
 * @date 2019/7/9
 */
public class DescribeRecordFileUrlResponse extends ResponseModel {
    private String recordFileUrl;

    public String getRecordFileUrl() {
        return recordFileUrl;
    }

    public void setRecordFileUrl(String recordFileUrl) {
        this.recordFileUrl = recordFileUrl;
    }
}
