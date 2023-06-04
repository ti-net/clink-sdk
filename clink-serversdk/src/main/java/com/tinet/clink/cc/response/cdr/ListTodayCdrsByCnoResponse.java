package com.tinet.clink.cc.response.cdr;


import com.tinet.clink.cc.model.CdrIbRecordModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * 查询呼入通话记录列表响应
 *
 * @author huwk
 * @date 2018/10/23
 **/
public class ListTodayCdrsByCnoResponse extends ResponseModel {

    /**
     * 通话记录列表
     */
    private List<CdrIbRecordModel> cdrs;
    /**
     * 合计条数
     */
    private Integer total;

    public List<CdrIbRecordModel> getCdrs() {
        return cdrs;
    }

    public void setCdrs(List<CdrIbRecordModel> cdrs) {
        this.cdrs = cdrs;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
