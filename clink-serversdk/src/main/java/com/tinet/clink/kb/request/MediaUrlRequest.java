package com.tinet.clink.kb.request;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.MediaUrlResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 获取文件路径
 *
 * @author feizq
 * @date 2022/07/27
 **/
public class MediaUrlRequest extends AbstractRequestModel<MediaUrlResponse> {

    /**
     * 文件的key
     */
    private String fileKey;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 下载或预览
     */
    private Boolean isDownload;

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
        if (fileKey != null) {
            putQueryParameter("fileKey", fileKey);
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        if (fileName != null) {
            putQueryParameter("fileName", fileName);
        }
    }

    public Boolean getDownload() {
        return isDownload;
    }

    public void setDownload(Boolean download) {
        isDownload = download;
        if (isDownload != null) {
            putQueryParameter("isDownload", isDownload);
        }
    }

    public MediaUrlRequest() {
        super(PathEnum.MediaUrl.value(), HttpMethodType.GET);
    }

    @Override
    public Class<MediaUrlResponse> getResponseClass() {
        return MediaUrlResponse.class;
    }
}
