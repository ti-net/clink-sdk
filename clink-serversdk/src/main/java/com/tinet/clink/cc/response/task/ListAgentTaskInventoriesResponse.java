package com.tinet.clink.cc.response.task;

import com.tinet.clink.cc.model.TaskInventoryCompactModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * Class for:
 * 获取座席外呼任务明细列表 响应
 *
 * @author : Tinet-yinzk
 * @date : 2023/6/4 23:02
 */
public class ListAgentTaskInventoriesResponse extends ResponseModel {

    /**
     * 待处理记录数
     */
    private Integer pendingCount;
    /**
     * 外呼任务明细列表
     */
    private List<TaskInventoryCompactModel> taskInventories;

    public Integer getPendingCount() {
        return pendingCount;
    }

    public void setPendingCount(Integer pendingCount) {
        this.pendingCount = pendingCount;
    }

    public List<TaskInventoryCompactModel> getTaskInventories() {
        return taskInventories;
    }

    public void setTaskInventories(List<TaskInventoryCompactModel> taskInventories) {
        this.taskInventories = taskInventories;
    }
}
