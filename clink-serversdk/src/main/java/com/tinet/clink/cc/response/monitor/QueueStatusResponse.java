package com.tinet.clink.cc.response.monitor;

import com.tinet.clink.cc.model.QueueStatusModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * 查询队列状态响应对象
 *
 * @author wangll
 * @date 2019/11/20
 **/
public class QueueStatusResponse extends ResponseModel {

    private List<QueueStatusModel> queueStatus;

    public List<QueueStatusModel> getQueueStatus() {
        return queueStatus;
    }

    public void setQueueStatus(List<QueueStatusModel> queueStatus) {
        this.queueStatus = queueStatus;
    }
}
