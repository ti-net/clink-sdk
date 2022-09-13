package com.tinet.clink.livechat.request;

import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.response.ChatMessageResponse;

/**
 * @author Wangyl
 * @date 2020/7/29
 */
public class ChatMessageRequest extends AbstractRequestModel<ChatMessageResponse> {

    private String scrollId;
    private String date;
    private Integer limit;

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
        if (scrollId != null) {
            putQueryParameter("scrollId", scrollId);
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        if (date != null) {
            putQueryParameter("date", date);
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

    public ChatMessageRequest() {
        super(PathEnum.ChatMessage.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ChatMessageResponse> getResponseClass() {
        return ChatMessageResponse.class;
    }
}
