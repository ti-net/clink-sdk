package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.livechat.model.ChatBotModel;

import java.util.List;

/**
 * 机器人列表响应
 */
public class ListChatBotResponse extends ResponseModel {

    /**
     * 机器人列表
     */
    private List<ChatBotModel> bots;

    public List<ChatBotModel> getBots() {
        return bots;
    }

    public void setBots(List<ChatBotModel> bots) {
        this.bots = bots;
    }
}
