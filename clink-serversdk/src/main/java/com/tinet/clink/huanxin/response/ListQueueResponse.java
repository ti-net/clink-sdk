package com.tinet.clink.huanxin.response;

import com.tinet.clink.core.response.PagedResponse;
import com.tinet.clink.huanxin.model.QueueModel;

import java.util.List;

public class ListQueueResponse extends PagedResponse {

    private List<QueueModel> queues;

    public List<QueueModel> getQueues() {
        return queues;
    }

    public void setAgents(List<QueueModel> queues) {
        this.queues = queues;
    }
}
