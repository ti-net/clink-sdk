package com.tinet.clink.kb.request;

import com.tinet.clink.kb.PathEnum;
import com.tinet.clink.kb.response.ChatDescribeConversationResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * @author zhangpc
 * @since 2024/05/29
 */
public class ChatDescribeConversationRequest extends AbstractRequestModel<ChatDescribeConversationResponse>  {

    /**
     * 会话 id
     */
    private String conversationId;

    /**
     * 分页 偏移
     */
    private Integer offset;

    /**
     * 分页 条数
     */
    private Integer limit;


    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
        if (conversationId != null) {
            putQueryParameter("conversationId", conversationId);
        }
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (offset != null) {
            putQueryParameter("offset", offset);
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (limit != null) {
            putQueryParameter("limit", limit);
        }
    }

    public ChatDescribeConversationRequest() {
        super(PathEnum.ChatDescribeConversation.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ChatDescribeConversationResponse> getResponseClass() {
        return ChatDescribeConversationResponse.class;
    }
}
