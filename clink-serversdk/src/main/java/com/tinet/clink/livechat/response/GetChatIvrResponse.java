package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.livechat.model.ChatIvrModel;

/**
 * 查询路由导航详情响应
 */
public class GetChatIvrResponse extends ResponseModel {

    /**
     * 导航详情
     */
    private ChatIvrModel ivr;


    public ChatIvrModel getIvr() {
        return ivr;
    }

    public void setIvr(ChatIvrModel ivr) {
        this.ivr = ivr;
    }

}
