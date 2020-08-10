package com.tinet.clink.openapi.model.chat;

/**
 * 媒体文件消息内容
 *
 * @author wangzb
 * @date 2020/8/8
 */
public class MediaPayload extends MessagePayload {

    /**
     * 媒体文件Id
     */
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
