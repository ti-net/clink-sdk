package com.tinet.clink.cc.response.cdr;


import com.tinet.clink.cc.model.CdrObRecordDetailModel;
import com.tinet.clink.core.response.ResponseModel;

/**
 * 查询外呼通话记录详情响应
 *
 * @author huwk
 * @date 2018/10/24
 **/
public class DescribeCdrObResponse extends ResponseModel {

    /**
     * 主通话记录
     */
    private CdrObRecordDetailModel cdrOb;

    public CdrObRecordDetailModel getCdrOb() {
        return cdrOb;
    }

    public void setCdrOb(CdrObRecordDetailModel cdrOb) {
        this.cdrOb = cdrOb;
    }
}
