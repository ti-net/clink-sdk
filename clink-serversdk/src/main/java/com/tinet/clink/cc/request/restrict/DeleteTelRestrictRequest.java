package com.tinet.clink.cc.request.restrict;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.restrict.DeleteTelRestrictResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


import java.util.Objects;

/**
 * @author libin
 * @date 2021-12-13 11:13 上午
 */
public class DeleteTelRestrictRequest extends AbstractRequestModel<DeleteTelRestrictResponse> {

    private Integer restrictType;

    private String tel;

    public DeleteTelRestrictRequest() {
        super(PathEnum.DeleteTelRestrict.value(), HttpMethodType.POST);
    }

    @Override
    public Class<DeleteTelRestrictResponse> getResponseClass() {
        return DeleteTelRestrictResponse.class;
    }

    public void setRestrictType(Integer restrictType) {
        this.restrictType = restrictType;
        if (Objects.nonNull(restrictType)) {
            putQueryParameter("restrictType", restrictType);
        }
    }

    public void setTel(String tel) {
        this.tel = tel;
        if (Objects.nonNull(tel)) {
            putQueryParameter("tel", tel);
        }
    }


    public Integer getRestrictType() {
        return restrictType;
    }

    public String getTel() {
        return tel;
    }
}
