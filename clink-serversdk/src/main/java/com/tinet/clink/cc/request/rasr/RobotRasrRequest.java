package com.tinet.clink.cc.request.rasr;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.rasr.RobotRasrResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

import java.util.Objects;

/**
 * 查询AI对话转写记录
 *
 * @author wangpw
 * @date 2023年12月25日
 */

public class RobotRasrRequest extends AbstractRequestModel<RobotRasrResponse> {

    /**
     * 唯一标识
     */
    private String mainUniqueId;

    public RobotRasrRequest() {
        super(PathEnum.RobotRasr.value(), HttpMethodType.GET);
    }

    @Override
    public Class<RobotRasrResponse> getResponseClass() {
        return RobotRasrResponse.class;
    }


    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
        if (Objects.nonNull(mainUniqueId)) {
            putQueryParameter("mainUniqueId", mainUniqueId);
        }
    }

    public String getMainUniqueId() {
        return mainUniqueId;
    }

}
