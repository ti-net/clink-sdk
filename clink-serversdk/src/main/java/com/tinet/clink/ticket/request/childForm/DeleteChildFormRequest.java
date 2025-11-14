package com.tinet.clink.ticket.request.childForm;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.ticket.response.childForm.DeleteChildFormResponse;

/**
 * 工单子表单列表查询
 *
 * @author dengjie
 * @date: 2023/10/26
 **/
public class DeleteChildFormRequest extends AbstractRequestModel<DeleteChildFormResponse> {

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

    public DeleteChildFormRequest() {
        super(PathEnum.DeleteChildForm.value(), HttpMethodType.DELETE);
    }

    @Override
    public Class<DeleteChildFormResponse> getResponseClass() {
        return DeleteChildFormResponse.class;
    }

}