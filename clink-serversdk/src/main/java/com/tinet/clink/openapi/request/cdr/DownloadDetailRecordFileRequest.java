package com.tinet.clink.openapi.request.cdr;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.cdr.DownloadDetailRecordFileResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author wangli
 * @date 2022-02-25 11:06 AM
 */
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  DownloadDetailRecordFileRequest extends AbstractRequestModel<DownloadDetailRecordFileResponse> {

    /**
     * 通话记录唯一标识
     */
    private String mainUniqueId;

    /**
     * 通话记录uniqueId
     */
    private String uniqueId;

    /**
     *  非必选参数,不传该参数时获取普通mp3格式通话录音,
     *  传参数时获取双轨录音的某一侧录音: 1-客户侧,2-座席侧
     */
    private Integer recordSide;

    public DownloadDetailRecordFileRequest() {
        super(PathEnum.DownloadDetailRecordFile.value(), HttpMethodType.GET);
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
        if (uniqueId != null) {
            putQueryParameter("uniqueId", uniqueId);
        }
    }

    @Override
    public Class<DownloadDetailRecordFileResponse> getResponseClass() {
        return DownloadDetailRecordFileResponse.class;
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

}
