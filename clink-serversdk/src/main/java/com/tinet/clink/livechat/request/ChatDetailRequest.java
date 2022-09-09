package com.tinet.clink.livechat.request;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.ChatDetailResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author Wangyl
 * @date 2020/7/29
 */
public class ChatDetailRequest extends AbstractRequestModel<ChatDetailResponse> {

    public ChatDetailRequest() {
        super(PathEnum.ChatDetail.value(), HttpMethodType.GET);
    }

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
    @Override
    public Class<ChatDetailResponse> getResponseClass() {
        return ChatDetailResponse.class;
    }
}