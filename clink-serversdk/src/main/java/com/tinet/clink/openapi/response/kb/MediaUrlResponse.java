package com.tinet.clink.openapi.response.kb;

import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 文件路径响应实体
 *
 * @author feizq
 * @date 2022/07/27
 **/
public class MediaUrlResponse extends ResponseModel {

    private String mediaUrl;

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
}
