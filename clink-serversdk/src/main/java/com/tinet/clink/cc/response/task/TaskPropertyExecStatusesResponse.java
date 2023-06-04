package com.tinet.clink.cc.response.task;

import com.tinet.clink.cc.model.TaskPropertyExecStatusModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * Class for:
 * 外呼任务执行状态列表
 *
 * @author : Tinet-yinzk
 * @date: 2023/6/4 22:09
 */
public class TaskPropertyExecStatusesResponse extends ResponseModel {
    /**
     * 已处理状态列表
     */
    private List<TaskPropertyExecStatusModel> handledStatuses;
    /**
     * 待处理状态列表
     */
    private List<TaskPropertyExecStatusModel> notHandledStatuses;

    public List<TaskPropertyExecStatusModel> getHandledStatuses() {
        return handledStatuses;
    }

    public void setHandledStatuses(List<TaskPropertyExecStatusModel> handledStatuses) {
        this.handledStatuses = handledStatuses;
    }

    public List<TaskPropertyExecStatusModel> getNotHandledStatuses() {
        return notHandledStatuses;
    }

    public void setNotHandledStatuses(List<TaskPropertyExecStatusModel> notHandledStatuses) {
        this.notHandledStatuses = notHandledStatuses;
    }
}
