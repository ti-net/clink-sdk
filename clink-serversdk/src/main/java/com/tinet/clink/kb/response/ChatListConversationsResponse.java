package com.tinet.clink.kb.response;

import com.tinet.clink.kb.model.ChatConversationResponseModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * @author zhangpc
 * @since 2024/05/29
 */
public class ChatListConversationsResponse extends ResponseModel {

    private List<ChatConversationResponseModel> result;

    public List<ChatConversationResponseModel> getResult() {
        return result;
    }

    public void setResult(List<ChatConversationResponseModel> responseModels) {
        this.result = responseModels;
    }
}
