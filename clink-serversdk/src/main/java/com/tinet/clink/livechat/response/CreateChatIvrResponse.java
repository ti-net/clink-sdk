package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.livechat.model.ChatIvrModel;

/**
 * 创建路由导航响应
 */
public class CreateChatIvrResponse extends ResponseModel {

    /**
     * 新建导航
     */
    private ChatIvrModel ivr;


    public ChatIvrModel getIvr() {
        return ivr;
    }

    public void setIvr(ChatIvrModel ivr) {
        this.ivr = ivr;
    }

}
