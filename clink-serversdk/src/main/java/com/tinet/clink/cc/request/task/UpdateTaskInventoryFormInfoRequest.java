package com.tinet.clink.cc.request.task;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.model.CustomizeField;
import com.tinet.clink.cc.response.task.UpdateTaskInventoryFormInfoResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 更新外呼任务明细处理状态 请求
 *
 * @author : Tinet-yinzk
 * @date 2023/6/4 21:36
 **/
public class UpdateTaskInventoryFormInfoRequest extends AbstractRequestModel<UpdateTaskInventoryFormInfoResponse> {

    /**
     * 任务明细id
     */
    private Integer inventoryId;

    /**
     * 自定义字段
     */
    private CustomizeField[] customize;
    /**
     * 备注
     */
    private String remark;
    /**
     * 客户名称
     */
    private String customerName;

    public UpdateTaskInventoryFormInfoRequest() {
        super(PathEnum.UpdateTaskInventoryFormInfo.value(), HttpMethodType.POST);
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public CustomizeField[] getCustomize() {
        return customize;
    }

    public void setCustomize(CustomizeField[] customize) {
        this.customize = customize;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public Class<UpdateTaskInventoryFormInfoResponse> getResponseClass() {
        return UpdateTaskInventoryFormInfoResponse.class;
    }

}
