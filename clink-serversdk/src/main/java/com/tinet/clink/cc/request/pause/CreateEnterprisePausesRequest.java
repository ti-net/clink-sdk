package com.tinet.clink.cc.request.pause;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.pause.CreateEnterprisePausesResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


import java.util.Objects;

/**
 * @author libin
 * @date 2021-12-14 5:27 下午
 */
public class CreateEnterprisePausesRequest extends AbstractRequestModel<CreateEnterprisePausesResponse> {

    private String pauseStatus;

    private Integer isRest;

    private Integer isDefault;

    public CreateEnterprisePausesRequest() {
        super(PathEnum.CreateEnterprisePauses.value(), HttpMethodType.POST);
    }

    @Override
    public Class<CreateEnterprisePausesResponse> getResponseClass() {
        return CreateEnterprisePausesResponse.class;
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
