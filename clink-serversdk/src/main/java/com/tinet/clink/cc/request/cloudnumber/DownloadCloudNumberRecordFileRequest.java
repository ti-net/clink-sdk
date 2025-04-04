package com.tinet.clink.cc.request.cloudnumber;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.cloudnumber.DownloadCloudNumberRecordFileResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * 下载云手机通话录音文件请求
 *
 * @author Tinet-yinzk
 * @date 2024/03/12
 */
public class DownloadCloudNumberRecordFileRequest extends AbstractRequestModel<DownloadCloudNumberRecordFileResponse> {

    /**
     * 通话记录唯一标识
     */
    private String uniqueId;


    public DownloadCloudNumberRecordFileRequest() {
        super(PathEnum.DownloadCloudNumberRecordFile.value(), HttpMethodType.GET);
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
    public Class<DownloadCloudNumberRecordFileResponse> getResponseClass() {
        return DownloadCloudNumberRecordFileResponse.class;
    }


}
