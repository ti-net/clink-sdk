package com.tinet.clink.cc.response.task;

import com.tinet.clink.cc.model.TaskPropertyModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * Class for:
 * 座席外呼任务列表
 * @author : Tinet-yinzk
 * @date 2023/6/4 22:52
 */
public class ListAgentTaskPropertiesResponse extends ResponseModel {
    /**
     * 外呼任务配置
     */
    private List<TaskPropertyModel> taskProperties;
    /**
     * 待处理任务数
     */
    private Integer pendingCount;

    public List<TaskPropertyModel> getTaskProperties() {
        return taskProperties;
    }

    public void setTaskProperties(List<TaskPropertyModel> taskProperties) {
        this.taskProperties = taskProperties;
    }

    public Integer getPendingCount() {
        return pendingCount;
    }

    public void setPendingCount(Integer pendingCount) {
        this.pendingCount = pendingCount;
    }
}
