package com.tinet.clink.cc.request.task;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.task.UpdateTaskInventoryHandleStatusResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

import java.util.Objects;


/**
 * 更新外呼任务明细处理状态 请求
 *
 * @author : Tinet-yinzk
 * @date 2023/6/4 21:36
 **/
public class UpdateTaskInventoryHandleStatusRequest extends AbstractRequestModel<UpdateTaskInventoryHandleStatusResponse> {

    /**
     * 任务明细id
     */
    private Integer inventoryId;
    /**
     * 处理状态，1：已处理；2：待处理；
     */
    private Integer handleStatus;
    /**
     * 处理状态详情
     */
    private String handleStatusDetail;

    public UpdateTaskInventoryHandleStatusRequest() {
        super(PathEnum.UpdateTaskInventoryHandleStatus.value(), HttpMethodType.POST);
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
        if (Objects.nonNull(inventoryId)) {
            putBodyParameter("inventoryId", inventoryId);
        }
    }

    public Integer getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(Integer handleStatus) {
        this.handleStatus = handleStatus;
        if (Objects.nonNull(handleStatus)) {
            putBodyParameter("handleStatus", handleStatus);
        }
    }

    public String getHandleStatusDetail() {
        return handleStatusDetail;
    }

    public void setHandleStatusDetail(String handleStatusDetail) {
        this.handleStatusDetail = handleStatusDetail;
        if (Objects.nonNull(handleStatusDetail)) {
            putBodyParameter("handleStatusDetail", handleStatusDetail);
        }
    }

    @Override
    public Class<UpdateTaskInventoryHandleStatusResponse> getResponseClass() {
        return UpdateTaskInventoryHandleStatusResponse.class;
    }

}
