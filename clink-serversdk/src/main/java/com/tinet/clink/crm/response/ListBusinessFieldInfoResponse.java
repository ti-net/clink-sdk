package com.tinet.clink.crm.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.crm.model.BusinessFieldModel;

import java.util.List;

/**
 * @author haoy
 * @date 2023/08/14
 */
public class ListBusinessFieldInfoResponse extends ResponseModel {

    private List<BusinessFieldModel> businessFields;

    public List<BusinessFieldModel> getBusinessFields() {
        return businessFields;
    }

    public void setBusinessFields(List<BusinessFieldModel> businessFields) {
        this.businessFields = businessFields;
    }
}
