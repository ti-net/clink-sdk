package com.tinet.clink.openapi.request.config.enterprise.pause;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.enterprise.pause.AddEnterprisePausesResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.Objects;

/**
 * @author libin
 * @date 2021-12-14 5:27 下午
 */
public class AddEnterprisePausesRequest extends AbstractRequestModel<AddEnterprisePausesResponse> {

    private String pauseStatus;

    private Integer isRest;

    private Integer isDefault;

    public AddEnterprisePausesRequest() {
        super(PathEnum.AddEnterprisePauses.value(), HttpMethodType.POST);
    }

    @Override
    public Class<AddEnterprisePausesResponse> getResponseClass() {
        return AddEnterprisePausesResponse.class;
    }

    public void setPauseStatus(String pauseStatus) {
        this.pauseStatus = pauseStatus;
        if (Objects.nonNull(pauseStatus)) {
            putBodyParameter("pauseStatus", pauseStatus);
        }
    }

    public void setIsRest(Integer isRest) {
        this.isRest = isRest;
        if (Objects.nonNull(isRest)) {
            putBodyParameter("isRest", isRest);
        }
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
        if (Objects.nonNull(isDefault)) {
            putBodyParameter("isDefault", isDefault);
        }
    }

    public String getPauseStatus() {
        return pauseStatus;
    }

    public Integer getIsRest() {
        return isRest;
    }

    public Integer getIsDefault() {
        return isDefault;
    }
}
