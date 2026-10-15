package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.livechat.model.ChatIvrNodeModel;

/**
 * 查询导航节点响应
 */
public class GetChatIvrNodeResponse extends ResponseModel {

    /**
     * 节点
     */
    private ChatIvrNodeModel node;


    public ChatIvrNodeModel getNode() {
        return node;
    }

    public void setNode(ChatIvrNodeModel node) {
        this.node = node;
    }

}
