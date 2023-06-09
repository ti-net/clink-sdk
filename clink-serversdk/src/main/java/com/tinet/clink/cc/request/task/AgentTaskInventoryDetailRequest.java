package com.tinet.clink.cc.request.task;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.task.AgentTaskInventoryDetailResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

import java.util.Objects;


/**
 * 获取座席外呼任务明细详情 请求
 *
 * @author : Tinet-yinzk
 * @date 2023/6/4 21:36
 **/
public class AgentTaskInventoryDetailRequest extends AbstractRequestModel<AgentTaskInventoryDetailResponse> {

    /**
     * 座席工号
     */
    private String cno;
    /**
     * 任务id
     */
    private Integer taskId;
    /**
     * 任务明细id
     */
    private Integer inventoryId;
    /**
     * 手机号码
     */
    private String customerTel;


    public AgentTaskInventoryDetailRequest() {
        super(PathEnum.AgentTaskInventoryDetail.value(), HttpMethodType.GET);
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (Objects.nonNull(cno)) {
            putQueryParameter("cno", cno);
        }
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
        if (Objects.nonNull(taskId)) {
            putQueryParameter("taskId", taskId);
        }
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
        if (Objects.nonNull(inventoryId)) {
            putQueryParameter("inventoryId", inventoryId);
        }
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
        if (Objects.nonNull(customerTel)) {
            putQueryParameter("customerTel", customerTel);
        }
    }

    @Override
    public Class<AgentTaskInventoryDetailResponse> getResponseClass() {
        return AgentTaskInventoryDetailResponse.class;
    }

}
