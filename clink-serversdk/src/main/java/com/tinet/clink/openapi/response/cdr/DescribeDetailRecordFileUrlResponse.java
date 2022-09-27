package com.tinet.clink.openapi.response.cdr;


import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 查询外呼通话记录详情响应
 *
 * @author huwk
 * @date 2018/10/24
 **/
/**
 * 获取从通话记录录音地址
 *
 * @author liuzy
 * @date 2020/11/05
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DescribeDetailRecordFileUrlResponse extends ResponseModel {
    private String recordFileUrl;

    public String getRecordFileUrl() {
        return recordFileUrl;
    }

    public void setRecordFileUrl(String recordFileUrl) {
        this.recordFileUrl = recordFileUrl;
    }
}