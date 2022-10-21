package com.tinet.clink.openapi.request.cdr;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.cdr.CreateInvestigationsResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author libin
 * @date 2022-04-06 11:01 上午
 */
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  CreateInvestigationsRequest extends AbstractRequestModel<CreateInvestigationsResponse> {

    /**
     * 唯一标识
     */
    private String mainUniqueId;

    /**
     * 满意度记录值
     */
    private String keys;

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
        if (mainUniqueId != null) {
            putBodyParameter("mainUniqueId", mainUniqueId);
        }
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
        if (keys != null) {
            putBodyParameter("keys", keys);
        }
    }

    public CreateInvestigationsRequest() {
        super(PathEnum.CreateInvestigations.value(), HttpMethodType.POST);
    }

    @Override
    public Class<CreateInvestigationsResponse> getResponseClass() {
        return CreateInvestigationsResponse.class;
    }
}
