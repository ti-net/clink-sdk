package com.tinet.clink.cc.request.task;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.task.TaskPropertyFormResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 外呼任务表单模版
 *
 * @author : Tinet-yinzk
 * @date 2023/6/4 21:36
 **/
public class TaskPropertyFormRequest extends AbstractRequestModel<TaskPropertyFormResponse> {

    /**
     * 外呼任务id
     */
    private Integer taskId;

    public TaskPropertyFormRequest() {
        super(PathEnum.TaskPropertyForm.value(), HttpMethodType.GET);
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    @Override
    public Class<TaskPropertyFormResponse> getResponseClass() {
        return TaskPropertyFormResponse.class;
    }

}
