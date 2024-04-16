package com.tinet.smartlink.openapi.request.sqc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.ScrollResponse;
import lombok.Getter;

import java.util.Date;


@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeComScrollRequest extends BaseRequest<ScrollResponse> {
    private String cursor;
    private Integer limit = 10;
    private Date channelTimeBegin;
    private Date channelTimeEnd;

    public void setCursor(String cursor) {
        this.cursor = cursor;
        putBodyParameter("cursor", cursor);
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        putBodyParameter("limit", limit);
    }

    public void setChatTimeBegin(Date chatTimeBegin) {
        this.channelTimeBegin = chatTimeBegin;
        putBodyParameter("channelTimeBegin", chatTimeBegin);
    }

    public void setChatTimeEnd(Date chatTimeEnd) {
        this.channelTimeEnd = chatTimeEnd;
        putBodyParameter("channelTimeEnd", chatTimeEnd);
    }

    public WeComScrollRequest() {
        super("/weCom/scroll", HttpMethodType.POST);
    }

    @Override
    public Class<ScrollResponse> getResponseClass() {
        return ScrollResponse.class;
    }
}
