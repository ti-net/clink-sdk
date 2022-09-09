package com.tinet.clink.cc.request.rasr;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.rasr.ListRasrResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.Objects;

/**
 * 查询rasr转写记录请求
 *
 * @author libin
 * @date 2021-12-13 10:31 上午
 */

public class ListRasrRequest extends AbstractRequestModel<ListRasrResponse> {

    /**
     * 唯一标识
     */
    private String mainUniqueId;

    public ListRasrRequest() {
        super(PathEnum.BotAsr.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListRasrResponse> getResponseClass() {
        return ListRasrResponse.class;
    }




    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
        if (Objects.nonNull(mainUniqueId) && !mainUniqueId.equals("")) {
            putQueryParameter("mainUniqueId", mainUniqueId);
        }
    }


    public String getMainUniqueId() {
        return mainUniqueId;
    }

}
