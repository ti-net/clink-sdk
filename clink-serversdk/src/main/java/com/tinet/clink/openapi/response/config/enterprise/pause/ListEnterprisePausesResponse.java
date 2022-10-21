package com.tinet.clink.openapi.response.config.enterprise.pause;

import com.tinet.clink.openapi.model.EnterprisePauseModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * @author wangll
 */
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  ListEnterprisePausesResponse extends ResponseModel {

    private List<EnterprisePauseModel> enterprisePauses;

    public List<EnterprisePauseModel> getEnterprisePauses() {
        return enterprisePauses;
    }

    public void setEnterprisePauses(List<EnterprisePauseModel> enterprisePauses) {
        this.enterprisePauses = enterprisePauses;
    }
}
