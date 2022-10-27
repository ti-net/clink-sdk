package com.tinet.clink.openapi.response.config.queue;

import com.tinet.clink.openapi.model.QueueDetailModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 删除队列响应
 *
 * @author lizy
 * @date 2018/10/25
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DescribeQueueResponse extends ResponseModel {


    private QueueDetailModel queue;


    public QueueDetailModel getQueue() {
        return queue;
    }

    public void setQueue(QueueDetailModel queue) {
        this.queue = queue;
    }
}
