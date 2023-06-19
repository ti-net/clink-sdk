package com.tinet.clink.cc.response.task;

import com.tinet.clink.core.response.ResponseModel;

/**
 * Class for:
 * 获取座席外呼任务明细详情 响应
 *
 * @author : Tinet-yinzk
 * @date : 2023/6/4 23:02
 */
public class UpdateTaskInventoryHandleStatusResponse extends ResponseModel {

    /**
     * 操作结果
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
