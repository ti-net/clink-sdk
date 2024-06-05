package com.tinet.clink.kb.response;

import com.tinet.clink.kb.model.ChatQaResponseModel;
import com.tinet.clink.core.response.ResponseModel;

/**
 * @author zhangpc
 * @since 2024/05/29
 */
public class ChatConversationResponse extends ResponseModel {

    private ChatQaResponseModel result;

    public ChatQaResponseModel getResult() {
        return result;
    }

    public void setResult(ChatQaResponseModel responseModel) {
        this.result = responseModel;
    }
}
