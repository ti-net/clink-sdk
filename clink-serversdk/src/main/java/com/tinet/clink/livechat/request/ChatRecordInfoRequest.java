package com.tinet.clink.livechat.request;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.ChatRecordResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author zhaozq
 * @date 2022.03.23
 */
public class ChatRecordInfoRequest extends AbstractRequestModel<ChatRecordResponse> {

    private String date;
    private String mainUniqueId;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        if (date != null) {
            putQueryParameter("date", date);
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

    public ChatRecordInfoRequest() {
        super(PathEnum.ChatRecordInfo.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ChatRecordResponse> getResponseClass() {
        return ChatRecordResponse.class;
    }
}
