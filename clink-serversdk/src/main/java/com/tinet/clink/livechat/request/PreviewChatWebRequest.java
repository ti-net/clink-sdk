package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.response.PreviewChatWebResponse;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;

/**
 * 获取网页接入代码和 H5 地址
 */
public class PreviewChatWebRequest extends AbstractStatRequest<PreviewChatWebResponse> {

    /**
     * 接入号
     */
    private String accessId;

    public PreviewChatWebRequest() {
        super(PathEnum.PreviewChatWeb.value(), HttpMethodType.POST);
    }

    @Override
    public Class<PreviewChatWebResponse> getResponseClass() {
        return PreviewChatWebResponse.class;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
        if (accessId != null) {
            putQueryParameter("accessId", accessId);
        }
    }

}
