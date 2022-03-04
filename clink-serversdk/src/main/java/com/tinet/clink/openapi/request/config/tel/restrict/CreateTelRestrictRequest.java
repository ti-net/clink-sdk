package com.tinet.clink.openapi.request.config.tel.restrict;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.tel.restrict.CreateTelRestrictResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.Date;
import java.util.Objects;

/**
 * 创建黑白名单请求
 *
 * @author libin
 * @date 2021-12-13 10:31 上午
 */

public class CreateTelRestrictRequest extends AbstractRequestModel<CreateTelRestrictResponse> {


    /**
     * 呼叫限制类型 1:黑名单 2:白名单
     */
    private Integer restrictType;

    /**
     * 类型 1:呼入 2:外呼
     */
    private Integer type;

    /**
     * 电话号码类型 1:手机号码 2:地区1 3:未知号码
     */
    private Integer telType;

    /**
     * 电话号码
     */
    private String tel;

    /**
     * 黑白名单到期时间，为空则永久有效
     */
    private Date expirationTime;


    public CreateTelRestrictRequest() {
        super(PathEnum.CreateTelRestrict.value(), HttpMethodType.POST);
    }

    @Override
    public Class<CreateTelRestrictResponse> getResponseClass() {
        return CreateTelRestrictResponse.class;
    }


    public void setRestrictType(Integer restrictType) {
        this.restrictType = restrictType;
        if (Objects.nonNull(restrictType)) {
            putBodyParameter("restrictType", restrictType);
        }
    }

    public void setType(Integer type) {
        this.type = type;
        if (Objects.nonNull(type)) {
            putBodyParameter("type", type);
        }
    }

    public void setTelType(Integer telType) {
        this.telType = telType;
        if (Objects.nonNull(telType)) {
            putBodyParameter("telType", telType);
        }
    }

    public void setTel(String tel) {
        this.tel = tel;
        if (Objects.nonNull(tel) && !tel.equals("")) {
            putBodyParameter("tel", tel);
        }
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
        if (Objects.nonNull(expirationTime) ) {
            putBodyParameter("expirationTime", expirationTime);
        }
    }

    public Integer getRestrictType() {
        return restrictType;
    }

    public Integer getType() {
        return type;
    }

    public Integer getTelType() {
        return telType;
    }

    public String getTel() {
        return tel;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }
}
