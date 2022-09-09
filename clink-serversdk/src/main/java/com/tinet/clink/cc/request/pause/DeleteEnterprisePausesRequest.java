package com.tinet.clink.cc.request.pause;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.enterprise.pause.DeleteEnterprisePausesResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.Objects;

/**
 * @author libin
 * @date 2021-12-14 5:35 下午
 */
public class DeleteEnterprisePausesRequest extends AbstractRequestModel<DeleteEnterprisePausesResponse> {

    private String pauseStatus;

    public DeleteEnterprisePausesRequest() {
        super(PathEnum.DeleteEnterprisePauses.value(), HttpMethodType.POST);
    }

    @Override
    public Class<DeleteEnterprisePausesResponse> getResponseClass() {
        return DeleteEnterprisePausesResponse.class;
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
