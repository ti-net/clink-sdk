package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.SessionMessageResponse;

/**
 * 聊天记录查询
 *
 * @author wangbin
 * @since 2024/12/10
 */
public class SessionMessageRequest extends AbstractStatRequest<SessionMessageResponse> {

    private String mainUniqueId;

    public SessionMessageRequest() {
        super(PathEnum.ListSessionMessages.value(), HttpMethodType.POST);
    }

    @Override
    public Class<SessionMessageResponse> getResponseClass() {
        return SessionMessageResponse.class;
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
}
