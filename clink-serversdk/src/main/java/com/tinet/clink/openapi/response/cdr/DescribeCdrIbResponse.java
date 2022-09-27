package com.tinet.clink.openapi.response.cdr;

import com.tinet.clink.openapi.model.CdrIbRecordDetailModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 查询呼入通话记录详情响应
 *
 * @author huwk
 * @date 2018/10/24
 **/
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  DescribeCdrIbResponse extends ResponseModel {

    /**
     * 主通话记录
     */
    private CdrIbRecordDetailModel cdrIb;

    public CdrIbRecordDetailModel getCdrIb() {
        return cdrIb;
    }

    public void setCdrIb(CdrIbRecordDetailModel cdrIb) {
        this.cdrIb = cdrIb;
    }
}
