package com.tinet.clink.cc.response.task;

import com.tinet.clink.cc.model.TaskInventoryDetailModel;
import com.tinet.clink.core.response.ResponseModel;

/**
 * Class for:
 * 获取座席外呼任务明细详情 响应
 *
 * @author : Tinet-yinzk
 * @date : 2023/6/4 23:02
 */
public class AgentTaskInventoryDetailResponse extends ResponseModel {

    /**
     * 任务明细详情
     */
    private TaskInventoryDetailModel taskInventory;

    public TaskInventoryDetailModel getTaskInventory() {
        return taskInventory;
    }

    public void setTaskInventory(TaskInventoryDetailModel taskInventory) {
        this.taskInventory = taskInventory;
    }
}
