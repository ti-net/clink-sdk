package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 获取媒体文件URL的响应
 *
 * @author wangzb
 * @date 2020/8/8
 */
public class MediaUrlResponse extends ResponseModel {

    /**
     * 媒体文件临时URL，有效期2分钟
     */
    private String mediaUrl;

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
}
