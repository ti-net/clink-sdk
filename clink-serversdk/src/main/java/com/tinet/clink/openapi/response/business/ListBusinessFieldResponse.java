package com.tinet.clink.openapi.response.business;

import com.tinet.clink.openapi.model.BusinessCustomizeFields;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**查询业务记录的自定义字段
 * @author liuhy
 * @date 2020/01/09
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListBusinessFieldResponse extends ResponseModel {

    private List<BusinessCustomizeFields> businessCustomizeParams;

    public List<BusinessCustomizeFields> getBusinessCustomizeParams() {
        return businessCustomizeParams;
    }

    public void setBusinessCustomizeParams(List<BusinessCustomizeFields> businessCustomizeParams) {
        this.businessCustomizeParams = businessCustomizeParams;
    }
}
