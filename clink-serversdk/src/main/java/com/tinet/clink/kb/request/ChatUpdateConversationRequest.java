package com.tinet.clink.kb.request;

import com.tinet.clink.kb.PathEnum;
import com.tinet.clink.kb.response.ChatUpdateConversationResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * @author zhangpc
 * @since 2024/05/29
 */
public class ChatUpdateConversationRequest extends AbstractRequestModel<ChatUpdateConversationResponse> {


    /**
     * 会话 id
     */
    private String conversationId;

    /**
     * 标题
     */
    private String title;

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
        if (conversationId != null) {
            putBodyParameter("conversationId", conversationId);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        if (title != null) {
            putBodyParameter("title", title);
        }
    }

    public ChatUpdateConversationRequest() {
        super(PathEnum.ChatUpdateConversation.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ChatUpdateConversationResponse> getResponseClass() {
        return ChatUpdateConversationResponse.class;
    }
}
