package com.tinet.clink.cc.response.cloudnumber;


import com.tinet.clink.cc.model.CloudNumberCdrModel;
import com.tinet.clink.core.response.PagedResponse;

import java.util.List;

/**
 * 查询云手机通话记录列表响应
 *
 * @author Tinet-yinzk
 * @date 2024/03/12
 **/
public class ListCloudNumberCdrsResponse extends PagedResponse {

    /**
     * 主通话记录列表
     */
    private List<CloudNumberCdrModel> cloudNumberCdrs;

    public List<CloudNumberCdrModel> getCloudNumberCdrs() {
        return cloudNumberCdrs;
    }

    public void setCloudNumberCdrs(List<CloudNumberCdrModel> cloudNumberCdrs) {
        this.cloudNumberCdrs = cloudNumberCdrs;
    }
}
