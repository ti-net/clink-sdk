package com.tinet.clink.openapi.request.config.tel.restrict;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.tel.restrict.ListTelRestrictResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.Objects;

/**
 * @author libin
 * @date 2021-12-13 1:45 下午
 */
public class ListTelRestrictRequest extends AbstractRequestModel<ListTelRestrictResponse> {

    private Integer offset;

    private Integer limit;

    private Integer restrictType;

    private Integer type;

    private String tel;

    private Integer telType;

    private String description;

    private Integer expirationFlag;

    public ListTelRestrictRequest() {
        super(PathEnum.ListTelRestrict.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListTelRestrictResponse> getResponseClass() {
        return ListTelRestrictResponse.class;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (Objects.nonNull(offset)) {
            putQueryParameter("offset", offset);
        }
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (Objects.nonNull(limit)) {
            putQueryParameter("limit", limit);
        }
    }

    public void setRestrictType(Integer restrictType) {
        this.restrictType = restrictType;
        if (Objects.nonNull(restrictType)) {
            putQueryParameter("restrictType", restrictType);
        }
    }

    public void setType(Integer type) {
        this.type = type;
        if (Objects.nonNull(type)) {
            putQueryParameter("type", type);
        }
    }

    public void setTel(String tel) {
        this.tel = tel;
        if (Objects.nonNull(tel)) {
            putQueryParameter("tel", tel);
        }
    }

    public void setTelType(Integer telType) {
        this.telType = telType;
        if (Objects.nonNull(telType)) {
            putQueryParameter("telType", telType);
        }
    }

    public void setDescription(String description) {
        this.description = description;
        if (Objects.nonNull(description)) {
            putQueryParameter("description", description);
        }
    }

    public void setExpirationFlag(Integer expirationFlag) {
        this.expirationFlag = expirationFlag;
        if (Objects.nonNull(expirationFlag)) {
            putQueryParameter("expirationFlag", expirationFlag);
        }
    }

    public Integer getExpirationFlag() {
        return expirationFlag;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getRestrictType() {
        return restrictType;
    }

    public Integer getType() {
        return type;
    }

    public String getTel() {
        return tel;
    }

    public Integer getTelType() {
        return telType;
    }

    public String getDescription() {
        return description;
    }
}
