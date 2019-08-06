package com.tinet.clink.openapi.response.config.queue;

import com.tinet.clink.openapi.model.QueueSearchResultModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 查询队列列表响应
 *
 * @author lizy
 * @date 2018/10/25
 */
public class ListQueuesResponse extends PagedResponse {

    private List<QueueSearchResultModel> queues;

    public List<QueueSearchResultModel> getQueues() {
        return queues;
    }

    public void setQueues(List<QueueSearchResultModel> queues) {
        this.queues = queues;
    }
}
