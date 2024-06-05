package com.tinet.clink.kb.request;

import com.tinet.clink.kb.PathEnum;
import com.tinet.clink.kb.response.ChatListConversationsResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * @author zhangpc
 * @since 2024/05/29
 */
public class ChatListConversationsRequest extends AbstractRequestModel<ChatListConversationsResponse> {

    /**
     * 用户 Id
     */
    private Integer userId;

    /**
     * 分页 偏移
     */
    private Integer offset;

    /**
     * 分页 条数
     */
    private Integer limit;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
        if (userId != null) {
            putQueryParameter("userId", userId);
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

    public ChatListConversationsRequest() {
        super(PathEnum.ChatListConversations.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ChatListConversationsResponse> getResponseClass() {
        return ChatListConversationsResponse.class;
    }
}
