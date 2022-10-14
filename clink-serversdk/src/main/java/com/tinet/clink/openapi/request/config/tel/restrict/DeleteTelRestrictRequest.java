package com.tinet.clink.openapi.request.config.tel.restrict;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.tel.restrict.DeleteTelRestrictResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.Objects;

/**
 * @author libin
 * @date 2021-12-13 11:13 上午
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DeleteTelRestrictRequest extends AbstractRequestModel<DeleteTelRestrictResponse> {

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
