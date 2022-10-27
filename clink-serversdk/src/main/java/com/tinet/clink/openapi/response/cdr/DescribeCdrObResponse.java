package com.tinet.clink.openapi.response.cdr;

import com.tinet.clink.openapi.model.CdrObRecordDetailModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 查询外呼通话记录详情响应
 *
 * @author huwk
 * @date 2018/10/24
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DescribeCdrObResponse extends ResponseModel {

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
