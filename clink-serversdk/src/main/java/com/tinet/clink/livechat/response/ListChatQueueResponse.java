package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.livechat.model.ChatQueueModel;

import java.util.List;

/**
 * 队列列表响应
 */
public class ListChatQueueResponse extends ResponseModel {

    /**
     * 队列列表
     */
    private List<ChatQueueModel> queues;

    public List<ChatQueueModel> getQueues() {
        return queues;
    }

    public void setQueues(List<ChatQueueModel> queues) {
        this.queues = queues;
    }
}
