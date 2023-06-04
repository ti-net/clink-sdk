package com.tinet.clink.cc.response.task;

import com.tinet.clink.cc.model.TaskFormModel;
import com.tinet.clink.core.response.ResponseModel;

/**
 * Class for:
 * 外呼任务表单模版
 *
 * @author : Tinet-yinzk
 * @date 2023/6/4 22:09
 */
public class TaskPropertyFormResponse extends ResponseModel {

    private TaskFormModel taskForm;

    public TaskFormModel getTaskForm() {
        return taskForm;
    }

    public void setTaskForm(TaskFormModel taskForm) {
        this.taskForm = taskForm;
    }
}
