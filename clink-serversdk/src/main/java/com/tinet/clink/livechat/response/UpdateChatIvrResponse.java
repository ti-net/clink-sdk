package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.livechat.model.ChatIvrModel;

/**
 * 更新路由导航响应
 */
public class UpdateChatIvrResponse extends ResponseModel {

    /**
     * 更新后的导航
     */
    private ChatIvrModel ivr;


    public ChatIvrModel getIvr() {
        return ivr;
    }

    public void setIvr(ChatIvrModel ivr) {
        this.ivr = ivr;
    }

}
