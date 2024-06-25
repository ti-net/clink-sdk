package com.tinet.clink.aikb.request;

import com.tinet.clink.aikb.PathEnum;
import com.tinet.clink.aikb.model.ChatSessionModel;
import com.tinet.clink.aikb.response.ChatSessionPushResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

import java.util.List;

/**
 * @author houwd
 * @since 2024/06/25
 */
public class ChatSessionPushRequest extends AbstractRequestModel<ChatSessionPushResponse> {

    private List<ChatSessionModel> sessions;

    public List<ChatSessionModel> getSessions() {
        return sessions;
    }

    public void setSessions(List<ChatSessionModel> sessions) {
        this.sessions = sessions;
    }

    public ChatSessionPushRequest() {
        super(PathEnum.PushChatSession.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ChatSessionPushResponse> getResponseClass() {
        return ChatSessionPushResponse.class;
    }
}
