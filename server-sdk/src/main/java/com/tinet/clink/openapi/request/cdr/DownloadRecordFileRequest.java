package com.tinet.clink.openapi.request.cdr;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.cdr.DownloadRecordFileResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 下载通话录音文件请求
 *
 * @author Wangyl
 * @date 2019/7/9
 */
public class DownloadRecordFileRequest extends AbstractRequestModel<DownloadRecordFileResponse> {

    /**
     * 通话记录唯一标识
     */
    private String mainUniqueId;

    public DownloadRecordFileRequest() {
        super(PathEnum.DownloadRecordFile.value(), HttpMethodType.GET);
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
    public Class<DownloadRecordFileResponse> getResponseClass() {
        return DownloadRecordFileResponse.class;
    }
}
