package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.livechat.model.ChatTimeRulePageModel;

/**
 * 时间条件列表响应
 */
public class ListTimeRuleResponse extends ResponseModel {

    /**
     * 时间条件分页
     */
    private ChatTimeRulePageModel page;


    public ChatTimeRulePageModel getPage() {
        return page;
    }

    public void setPage(ChatTimeRulePageModel page) {
        this.page = page;
    }

}
