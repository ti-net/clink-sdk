package com.tinet.clink.openapi.response;

import com.tinet.clink.openapi.model.QueueDetailModel;

/**
 * 删除队列响应
 *
 * @author lizy
 * @date 2018/10/25
 */
public class DescribeQueueResponse extends ResponseModel {


    private QueueDetailModel queue;


    public QueueDetailModel getQueue() {
        return queue;
    }

    public void setQueue(QueueDetailModel queue) {
        this.queue = queue;
    }
}
