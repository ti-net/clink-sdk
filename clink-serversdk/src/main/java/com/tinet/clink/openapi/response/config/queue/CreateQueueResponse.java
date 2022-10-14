package com.tinet.clink.openapi.response.config.queue;

import com.tinet.clink.openapi.model.CreateQueueResultModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 新增队列响应
 *
 * @author lizy
 * @date 2018/10/25
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  CreateQueueResponse extends ResponseModel {

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
