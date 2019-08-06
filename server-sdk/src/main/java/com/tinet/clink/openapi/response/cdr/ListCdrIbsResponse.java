package com.tinet.clink.openapi.response.cdr;

import com.tinet.clink.openapi.model.CdrIbRecordModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 查询呼入通话记录列表响应
 *
 * @author huwk
 * @date 2018/10/23
 **/
public class ListCdrIbsResponse extends PagedResponse {

    /**
     * 主通话记录列表
     */
    List<CdrIbRecordModel> cdrIbs;

    public List<CdrIbRecordModel> getCdrIbs() {
        return cdrIbs;
    }

    public void setCdrIbs(List<CdrIbRecordModel> cdrIbs) {
        this.cdrIbs = cdrIbs;
    }
}
