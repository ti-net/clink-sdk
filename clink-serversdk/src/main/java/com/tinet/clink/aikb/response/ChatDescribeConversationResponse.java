package com.tinet.clink.aikb.response;

import com.tinet.clink.aikb.model.ChatDescribeConversationResponseModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * @author zhangpc
 * @since 2024/05/29
 */
public class ChatDescribeConversationResponse extends ResponseModel {

    private List<ChatDescribeConversationResponseModel> result;

    public List<ChatDescribeConversationResponseModel> getResult() {
        return result;
    }

    public void setResult(List<ChatDescribeConversationResponseModel> responseModels) {
        this.result = responseModels;
    }
}
