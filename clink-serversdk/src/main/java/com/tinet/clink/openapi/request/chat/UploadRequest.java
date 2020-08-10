package com.tinet.clink.openapi.request.chat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.UploadResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.io.InputStream;

/**
 * 上传媒体文件
 *
 * @author wangzb
 * @date 2020/8/8
 */
public class UploadRequest extends AbstractRequestModel<UploadResponse> {

    public UploadRequest() {
        super(PathEnum.ChatUpload.value(), HttpMethodType.GET);
    }

    @Override
    public Class<UploadResponse> getResponseClass() {
        return UploadResponse.class;
    }

    private Integer visitorId;

    private InputStream media;

    public Integer getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Integer visitorId) {
        this.visitorId = visitorId;
        if (visitorId != null) {
            putBodyParameter("visitorId", visitorId);
        }
    }

    public InputStream getMedia() {
        return media;
    }

    public void setMedia(InputStream media) {
        this.media = media;
        if (media != null) {
            putBodyParameter("media", media);
        }
    }
}
