package com.tinet.clink.cc.response.cloudnumber;

import com.tinet.clink.core.response.ResponseModel;

/**
 * 云手机通话录音地址响应
 *
 * @author Tinet-yinzk
 * @date 2024/03/12
 */
public class DescribeCloudNumberRecordFileResponse extends ResponseModel {

    private String recordFileUrl;

    public String getRecordFileUrl() {
        return recordFileUrl;
    }

    public void setRecordFileUrl(String recordFileUrl) {
        this.recordFileUrl = recordFileUrl;
    }

}
