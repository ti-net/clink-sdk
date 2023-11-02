package com.tinet.clink.ticket.request.childForm;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.ticket.response.childForm.ListChildFormResponse;

/**
 * 工单子表单列表查询
 *
 * @author dengjie
 * @date: 2023/10/26
 **/
public class ListChildFormRequest extends AbstractRequestModel<ListChildFormResponse> {

    /**
     * formId
     */
    private Integer formId;
    private String formName;
    /**
     * limit
     */
    private Integer limit;

    /**
     * offset
     */
    private Integer offset;


    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
        if (formId != null) {
            putQueryParameter("formId", formId);
        }
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
        if (formName != null) {
            putQueryParameter("formName", formName);
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (limit != null) {
            putQueryParameter("limit", limit);
        }
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (offset != null) {
            putQueryParameter("offset", offset);
        }
    }

    public ListChildFormRequest() {
        super(PathEnum.ListChildForm.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListChildFormResponse> getResponseClass() {
        return ListChildFormResponse.class;
    }

}