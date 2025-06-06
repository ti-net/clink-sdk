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
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DescribeRecordFileUrlRequest extends AbstractRequestModel<DescribeRecordFileUrlResponse> {

    /**
     * 通话记录唯一标识
     */
    private String mainUniqueId;

    /**
     *  非必选参数,不传该参数时获取普通mp3格式通话录音,
     *  传参数时获取双轨录音的某一侧录音: 1-客户侧,2-座席侧
     */
    private Integer recordSide;
    /**
     *  通话录音超时时长 默认一小时 范围 1-24
     */
    private Long timeout;

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

    public Integer getRecordSide() {
        return recordSide;
    }

    public void setRecordSide(Integer recordSide) {
        this.recordSide = recordSide;
        if (recordSide != null) {
            putQueryParameter("recordSide", recordSide);
        }
    }
    public Long getTimeout(){return timeout;}
    public void setTimeout(Long timeout){
        this.timeout = timeout;
        if (timeout != null) {
            putQueryParameter("timeout", timeout);
        }
    }

}
