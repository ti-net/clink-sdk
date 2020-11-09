package com.tinet.clink.openapi.request.cdr;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.cdr.DescribeDetailRecordFileUrlResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 获取从通话记录录音地址
 *
 * @author liuzy
 * @date 2020/11/05
 **/
public class DescribeDetailRecordFileUrlRequest extends AbstractRequestModel<DescribeDetailRecordFileUrlResponse> {
    /**
     * 通话记录唯一标识
     */
    private String mainUniqueId;



    private String uniqueId;

    /**
     *  非必选参数,不传该参数时获取普通mp3格式通话录音,
     *  传参数时获取双轨录音的某一侧录音: 1-客户侧,2-座席侧
     */

    private Integer recordSide;
    public DescribeDetailRecordFileUrlRequest() {
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

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public Class<DescribeDetailRecordFileUrlResponse> getResponseClass() {
        return DescribeDetailRecordFileUrlResponse.class;
    }
    public Integer getRecordSide(){
        return recordSide;
    }

    public void setRecordSide(Integer recordSide) {
        this.recordSide = recordSide;
        if (recordSide != null) {
            putQueryParameter("recordSide", recordSide);
        }
    }
}