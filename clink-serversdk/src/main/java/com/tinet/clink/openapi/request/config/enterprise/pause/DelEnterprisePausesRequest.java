package com.tinet.clink.openapi.request.config.enterprise.pause;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.enterprise.pause.DelEnterprisePausesResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.Objects;

/**
 * @author libin
 * @date 2021-12-14 5:35 下午
 */
public class DelEnterprisePausesRequest extends AbstractRequestModel<DelEnterprisePausesResponse> {

    private String pauseStatus;

    public DelEnterprisePausesRequest() {
        super(PathEnum.DelEnterprisePauses.value(), HttpMethodType.GET);
    }

    @Override
    public Class<DelEnterprisePausesResponse> getResponseClass() {
        return DelEnterprisePausesResponse.class;
    }

    public void setPauseStatus(String pauseStatus) {
        this.pauseStatus = pauseStatus;
        if (Objects.nonNull(pauseStatus)) {
            putQueryParameter("pauseStatus", pauseStatus);
        }
    }

    public String getPauseStatus() {
        return pauseStatus;
    }


}
