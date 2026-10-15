package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.livechat.model.ChatWebPreviewModel;

/**
 * 网页接入预览响应
 */
public class PreviewChatWebResponse extends ResponseModel {

    /**
     * 预览地址
     */
    private ChatWebPreviewModel preview;


    public ChatWebPreviewModel getPreview() {
        return preview;
    }

    public void setPreview(ChatWebPreviewModel preview) {
        this.preview = preview;
    }

}
