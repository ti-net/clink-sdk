package com.tinet.clink.openapi.request.chat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.ChatMessageResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author yuqiang
 * @date 2021/10/20
 */
public class ListChatMessageRequest extends AbstractRequestModel<ChatMessageResponse> {

    private String scrollId;
    private String date;
    private Integer limit;
    private String mainUniqueId;

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

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
        if (mainUniqueId != null) {
            putQueryParameter("mainUniqueId", mainUniqueId);
        }
    }

    public ListChatMessageRequest() {
        super(PathEnum.ChatMessageDetail.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ChatMessageResponse> getResponseClass() {
        return ChatMessageResponse.class;
    }
}
