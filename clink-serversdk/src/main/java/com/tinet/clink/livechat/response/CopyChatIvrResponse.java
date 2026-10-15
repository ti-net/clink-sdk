package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.livechat.model.ChatIvrModel;

/**
 * 复制路由导航响应
 */
public class CopyChatIvrResponse extends ResponseModel {

    /**
     * 复制结果
     */
    private ChatIvrModel ivr;


    public ChatIvrModel getIvr() {
        return ivr;
    }

    public void setIvr(ChatIvrModel ivr) {
        this.ivr = ivr;
    }

}
