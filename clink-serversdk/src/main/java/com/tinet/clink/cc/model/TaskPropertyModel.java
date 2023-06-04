package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class for:
 *  外呼任务配置（简版）
 * @author : Tinet-yinzk
 * @date 2023/6/4 22:50
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskPropertyModel {
    /**
     * 外呼任务id
     */
    private Integer taskId;
    /**
     * 外呼任务名称
     */
    private String name;
    /**
     * 任务状态，1：执行中；2：暂停；
     */
    private Integer status;
    /**
     * 未呼叫号码总数
     */
    private Integer count;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
