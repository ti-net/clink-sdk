package com.tinet.clink.openapi.response.cdr;

import com.tinet.clink.openapi.model.CdrObRecordModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 查询外呼通话记录列表响应
 *
 * @author huwk
 * @date 2018/10/24
 **/
public class ListCdrObsResponse extends PagedResponse {

    /**
     * 主通话记录列表
     */
    List<CdrObRecordModel> cdrObs;

    public List<CdrObRecordModel> getCdrObs() {
        return cdrObs;
    }

    public void setCdrObs(List<CdrObRecordModel> cdrObs) {
        this.cdrObs = cdrObs;
    }
}
