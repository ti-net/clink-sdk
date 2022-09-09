package com.tinet.clink.cc.response.queue;

import com.tinet.clink.openapi.model.CreateQueueResultModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 新增队列响应
 *
 * @author lizy
 * @date 2018/10/25
 */
public class CreateQueueResponse extends ResponseModel {

    /**
     * 新增队列的model,同传入参数
     */
    private CreateQueueResultModel queue;


    public CreateQueueResultModel getQueue() {
        return queue;
    }

    public void setQueue(CreateQueueResultModel queue) {
        this.queue = queue;
    }
}
