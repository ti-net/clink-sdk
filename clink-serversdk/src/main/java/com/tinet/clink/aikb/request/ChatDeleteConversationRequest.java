package com.tinet.clink.aikb.request;

import com.tinet.clink.aikb.PathEnum;
import com.tinet.clink.aikb.response.ChatDeleteConversationResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * @author zhangpc
 * @since 2024/05/29
 */
public class ChatDeleteConversationRequest extends AbstractRequestModel<ChatDeleteConversationResponse> {

    /**
     * 会话 id
     */
    private String conversationId;

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
        if (conversationId != null) {
            putQueryParameter("conversationId", conversationId);
        }
    }

    public ChatDeleteConversationRequest() {
        super(PathEnum.ChatDeleteConversation.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ChatDeleteConversationResponse> getResponseClass() {
        return ChatDeleteConversationResponse.class;
    }
}
