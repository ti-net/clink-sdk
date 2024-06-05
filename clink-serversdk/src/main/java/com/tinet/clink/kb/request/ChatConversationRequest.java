package com.tinet.clink.kb.request;

import com.tinet.clink.kb.PathEnum;
import com.tinet.clink.kb.response.ChatConversationResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * @author zhangpc
 * @since 2024/05/29
 */
public class ChatConversationRequest extends AbstractRequestModel<ChatConversationResponse> {

    /**
     * 用户 Id
     */
    private Integer userId;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 对话 ID，新对话第一条消息不传，且会返回在服务端生成的 ID，对话的后续消息必传，否则会被识别为对话
     */
    private String conversationId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
        if (userId != null) {
            putBodyParameter("userId", userId);
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        if (content != null) {
            putBodyParameter("content", content);
        }
    }


    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
        if (conversationId != null) {
            putBodyParameter("conversationId", conversationId);
        }
    }

    public ChatConversationRequest() {
        super(PathEnum.ChatConversation.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ChatConversationResponse> getResponseClass() {
        return ChatConversationResponse.class;
    }
}
