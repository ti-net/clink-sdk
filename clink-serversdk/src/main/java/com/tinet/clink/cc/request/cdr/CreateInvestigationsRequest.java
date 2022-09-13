package com.tinet.clink.cc.request.cdr;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.cdr.CreateInvestigationsResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * @author libin
 * @date 2022-04-06 11:01 上午
 */
public class CreateInvestigationsRequest extends AbstractRequestModel<CreateInvestigationsResponse> {

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
