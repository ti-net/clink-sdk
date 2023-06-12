package com.tinet.clink.cc.response.cdr;


import com.tinet.clink.cc.model.TodayCdrRecordModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * 查询座席今日通话记录列表
 *
 * @author yinzk
 * @date 2023/6/9
 **/
public class ListTodayCdrsByCnoResponse extends ResponseModel {

    /**
     * 通话记录列表
     */
    private List<TodayCdrRecordModel> cdrs;
    /**
     * 合计条数
     */
    private Integer total;


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<TodayCdrRecordModel> getCdrs() {
        return cdrs;
    }

    public void setCdrs(List<TodayCdrRecordModel> cdrs) {
        this.cdrs = cdrs;
    }
}
