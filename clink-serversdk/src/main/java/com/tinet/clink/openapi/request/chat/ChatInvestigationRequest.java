package com.tinet.clink.openapi.request.chat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.ChatInvestigationResponse;
import com.tinet.clink.openapi.response.chat.ChatRecordResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author Wangyl
 * @date 2020/7/29
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ChatInvestigationRequest extends AbstractRequestModel<ChatInvestigationResponse> {

    private String scrollId;
    private String date;
    private Integer limit;
    private String mainUniqueId;

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
        if (mainUniqueId != null) {
            putQueryParameter("mainUniqueId", mainUniqueId);
        }
    }

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

    public ChatInvestigationRequest() {
        super(PathEnum.ChatInvestigation.value(), HttpMethodType.GET);
    }



    @Override
    public Class<ChatInvestigationResponse> getResponseClass() {
        return ChatInvestigationResponse.class;
    }
}
