package com.tinet.clink.openapi.response.config.queue;

import com.tinet.clink.openapi.model.QueueUpdateModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 更新队列响应
 *
 * @author lizy
 * @date 2018/10/25
 */
public class UpdateQueueResponse extends ResponseModel {

    /**
     * 更新队列的model（同传入参数）
     */
    private QueueUpdateModel queue;

    public QueueUpdateModel getQueue() {
        return queue;
    }

    public void setQueue(QueueUpdateModel queue) {
        this.queue = queue;
    }
}
