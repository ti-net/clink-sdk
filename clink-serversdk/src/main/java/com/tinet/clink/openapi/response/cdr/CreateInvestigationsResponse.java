package com.tinet.clink.openapi.response.cdr;

import com.tinet.clink.openapi.model.CreateInvestigationsSaveModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author libin
 * @date 2022-04-06 11:01 上午
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  CreateInvestigationsResponse extends ResponseModel {

    private CreateInvestigationsSaveModel investigation;

    public CreateInvestigationsSaveModel getInvestigation() {
        return investigation;
    }

    public void setInvestigation(CreateInvestigationsSaveModel investigation) {
        this.investigation = investigation;
    }
}
