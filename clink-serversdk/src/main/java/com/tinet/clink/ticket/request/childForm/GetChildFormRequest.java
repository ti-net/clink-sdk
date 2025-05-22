package com.tinet.clink.ticket.request.childForm;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.ticket.response.childForm.GetChildFormResponse;

/**
 * 工单子表单列表查询
 *
 * @author dengjie
 * @date: 2023/10/26
 **/
public class GetChildFormRequest extends AbstractRequestModel<GetChildFormResponse> {

    /**
     * formId
     */
    private Integer formId;

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
        if (formId != null) {
            putQueryParameter("formId", formId);
        }
    }

    public GetChildFormRequest() {
        super(PathEnum.GetChildForm.value(), HttpMethodType.GET);
    }

    @Override
    public Class<GetChildFormResponse> getResponseClass() {
        return GetChildFormResponse.class;
    }

}