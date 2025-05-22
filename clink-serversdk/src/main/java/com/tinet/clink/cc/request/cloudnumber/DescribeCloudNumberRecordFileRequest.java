package com.tinet.clink.cc.request.cloudnumber;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.cloudnumber.DescribeCloudNumberRecordFileResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * 查询云手机通话录音地址请求
 *
 * @author Tinet-yinzk
 * @date 2024/03/12
 */
public class DescribeCloudNumberRecordFileRequest extends AbstractRequestModel<DescribeCloudNumberRecordFileResponse> {

    /**
     * 通话记录唯一标识
     */
    private String uniqueId;

    /**
     * 通话录音超时时长 默认一小时 范围 1-24
     */
    private Long timeout;

    public DescribeCloudNumberRecordFileRequest() {
        super(PathEnum.DescribeCloudNumberRecordFile.value(), HttpMethodType.GET);
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        if (uniqueId != null) {
            putQueryParameter("uniqueId", uniqueId);
        }
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
        if (timeout != null) {
            putQueryParameter("timeout", timeout);
        }
    }

    @Override
    public Class<DescribeCloudNumberRecordFileResponse> getResponseClass() {
        return DescribeCloudNumberRecordFileResponse.class;
    }

}
