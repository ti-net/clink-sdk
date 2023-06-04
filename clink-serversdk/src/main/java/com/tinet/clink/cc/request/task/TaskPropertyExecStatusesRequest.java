package com.tinet.clink.cc.request.task;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.task.TaskPropertyExecStatusesResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 外呼任务执行状态请求
 *
 * @author : Tinet-yinzk
 * @date 2023/6/4 21:36
 **/
public class TaskPropertyExecStatusesRequest extends AbstractRequestModel<TaskPropertyExecStatusesResponse> {


    public TaskPropertyExecStatusesRequest() {
        super(PathEnum.TaskPropertyExecStatuses.value(), HttpMethodType.GET);
    }


    @Override
    public Class<TaskPropertyExecStatusesResponse> getResponseClass() {
        return TaskPropertyExecStatusesResponse.class;
    }

}
