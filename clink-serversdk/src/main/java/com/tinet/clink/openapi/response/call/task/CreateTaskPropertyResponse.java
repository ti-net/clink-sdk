package com.tinet.clink.openapi.response.call.task;

import com.tinet.clink.openapi.model.CreateTaskPropertyResponseModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 外呼任务创建响应
 *
 * @author: wangpw
 * @date: 2022/5/17
 */
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  CreateTaskPropertyResponse extends ResponseModel {

    /**
     * 创建外呼任务响应
     */
    private CreateTaskPropertyResponseModel taskProperty;


    public CreateTaskPropertyResponseModel getTaskProperty() {
        return taskProperty;
    }

    public void setTaskProperty(CreateTaskPropertyResponseModel taskProperty) {
        this.taskProperty = taskProperty;
    }
}
