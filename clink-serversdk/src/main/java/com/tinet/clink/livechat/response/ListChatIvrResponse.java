package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.livechat.model.ChatIvrPageModel;

/**
 * 分页查询路由导航响应
 */
public class ListChatIvrResponse extends ResponseModel {

    /**
     * 分页对象
     */
    private ChatIvrPageModel page;


    public ChatIvrPageModel getPage() {
        return page;
    }

    public void setPage(ChatIvrPageModel page) {
        this.page = page;
    }

}
