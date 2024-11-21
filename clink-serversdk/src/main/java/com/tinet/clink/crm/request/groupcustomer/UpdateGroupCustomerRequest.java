package com.tinet.clink.crm.request.groupcustomer;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.crm.PathEnum;
import com.tinet.clink.crm.model.IdValue;
import com.tinet.clink.crm.response.groupCustomer.UpdateGroupCustomerResponse;

/**
 * @author yangcr
 * @description: 更新企微群客户请求
 * @date 2024/07/30
 */
public class UpdateGroupCustomerRequest extends AbstractRequestModel<UpdateGroupCustomerResponse> {

    /**
     * 更新方式  0: 更新/ 1: 可覆盖
     */
    private Integer renovate;

    /**
     * 客户资料id
     */
    private Integer id;

    /**
     * 访客标识
     */
    private String visitorId;

    /**
     * 客户姓名
     */
    private String name;

    /**
     * 自定义字段
     */
    private IdValue[] customize;

    /**
     * 客户标签
     */
    private Integer[] labelIds;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;

        if (id != null) {
            putBodyParameter("id", id);
        }
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;

        if (visitorId != null) {
            putBodyParameter("visitorId", visitorId);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

        if (name != null) {
            putBodyParameter("name", name);
        }
    }


    public IdValue[] getCustomize() {
        return customize;
    }

    public void setCustomize(IdValue[] customize) {
        this.customize = customize;

        if (customize != null) {
            putBodyParameter("customize", customize);
        }
    }


    public Integer getRenovate() {
        return renovate;
    }

    public void setRenovate(Integer renovate) {
        this.renovate = renovate;

        if (renovate != null) {
            putBodyParameter("renovate", renovate);
        }
    }

    public Integer[] getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(Integer[] labelIds) {
        this.labelIds = labelIds;
        if (labelIds != null) {
            putBodyParameter("labelIds", labelIds);
        }
    }

    public UpdateGroupCustomerRequest() {
        super(PathEnum.UpdateGroupCustomer.value(), HttpMethodType.POST);
    }

    @Override
    public Class<UpdateGroupCustomerResponse> getResponseClass() {
        return UpdateGroupCustomerResponse.class;
    }
}
