package com.tinet.clink.openapi.request.cdr;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.cdr.DescribeRecordFileUrlResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 查询通话录音地址请求
 *
 * @author Wangyl
 * @date 2019/7/9
 */
public class DescribeRecordFileUrlRequest extends AbstractRequestModel<DescribeRecordFileUrlResponse> {

    /**
     * 通话记录唯一标识
     */
    private String mainUniqueId;

    public DescribeRecordFileUrlRequest() {
        super(PathEnum.DescribeRecordFileUrl.value(), HttpMethodType.GET);
    }

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
        if (mainUniqueId != null) {
            putQueryParameter("mainUniqueId", mainUniqueId);
        }
    }

    @Override
    public Class<DescribeRecordFileUrlResponse> getResponseClass() {
        return DescribeRecordFileUrlResponse.class;
    }
}
