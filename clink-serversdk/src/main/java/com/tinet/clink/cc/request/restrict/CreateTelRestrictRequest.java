package com.tinet.clink.cc.request.restrict;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.restrict.CreateTelRestrictResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


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

    /**
     * 备注
     **/
    private String description;

    /**
     * 是否加密 1：加密 0：不加密（默认）
     */
    private Integer encrypt;

    /**
     * 加密类型 1：aes加密 （默认aes加密)
     */
    private Integer encryptType;


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

    public void setEncrypt(Integer encrypt) {
        this.encrypt = encrypt;
        if (Objects.nonNull(encrypt) ) {
            putBodyParameter("encrypt", encrypt);
        }
    }

    public void setEncryptType(Integer encryptType) {
        this.encryptType = encryptType;
        if (Objects.nonNull(encryptType) ) {
            putBodyParameter("encryptType", encryptType);
        }
    }

    public Integer getEncrypt() {
        return encrypt;
    }

    public Integer getEncryptType() {
        return encryptType;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
