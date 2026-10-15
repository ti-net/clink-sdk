package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.response.ListChatBotResponse;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;

/**
 * 只读机器人列表
 */
public class ListChatBotRequest extends AbstractStatRequest<ListChatBotResponse> {

    public ListChatBotRequest() {
        super(PathEnum.ListChatBot.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ListChatBotResponse> getResponseClass() {
        return ListChatBotResponse.class;
    }
}
