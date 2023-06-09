package com.tinet.clink.cc.response.cdr;


import com.tinet.clink.cc.model.CdrRecordModel;
import com.tinet.clink.core.response.PagedResponse;

import java.util.List;

/**
 * 查询历史通话记录
 *
 * @author yinzk
 * @date 2023/6/9
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
