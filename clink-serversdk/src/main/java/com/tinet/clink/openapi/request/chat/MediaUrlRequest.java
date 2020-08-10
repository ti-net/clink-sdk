package com.tinet.clink.openapi.request.chat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.MediaUrlResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 获取媒体文件URL
 *
 * @author wangzb
 * @date 2020/8/8
 */
public class MediaUrlRequest extends AbstractRequestModel<MediaUrlResponse> {

    public MediaUrlRequest() {
        super(PathEnum.ChatMediaUrl.value(), HttpMethodType.GET);
    }

    @Override
    public Class<MediaUrlResponse> getResponseClass() {
        return MediaUrlResponse.class;
    }

    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
        if (mediaId != null) {
            putBodyParameter("meidaId", mediaId);
        }
    }
}
