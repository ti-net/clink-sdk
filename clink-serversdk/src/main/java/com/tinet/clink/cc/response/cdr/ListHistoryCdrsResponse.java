package com.tinet.clink.cc.response.cdr;


import com.tinet.clink.cc.model.CdrRecordModel;
import com.tinet.clink.core.response.PagedResponse;

import java.util.List;

/**
 * 查询呼入通话记录列表响应
 *
 * @author huwk
 * @date 2018/10/23
 **/
public class ListHistoryCdrsResponse extends PagedResponse {
    private List<CdrRecordModel> cdrs;

    public List<CdrRecordModel> getCdrs() {
        return cdrs;
    }

    public void setCdrs(List<CdrRecordModel> cdrs) {
        this.cdrs = cdrs;
    }
}
