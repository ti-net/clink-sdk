package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 上传媒体文件的响应
 *
 * @author wangzb
 * @date 2020/8/8
 */
public class UploadResponse extends ResponseModel {

    /**
     * 媒体文件标识
     */
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
