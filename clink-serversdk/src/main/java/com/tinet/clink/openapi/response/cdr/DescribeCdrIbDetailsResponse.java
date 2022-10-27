package com.tinet.clink.openapi.response.cdr;

import com.tinet.clink.openapi.model.CdrIbDetailModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * 查询呼入通话记录明细详情响应
 *
 * @author huwk
 * @date 2018/10/24
 **/
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  DescribeCdrIbDetailsResponse extends ResponseModel {

    /**
     * 通话记录明细详情列表
     */
    private List<CdrIbDetailModel> cdrIbDetails;

    public List<CdrIbDetailModel> getCdrIbDetails() {
        return cdrIbDetails;
    }

    public void setCdrIbDetails(List<CdrIbDetailModel> cdrIbDetails) {
        this.cdrIbDetails = cdrIbDetails;
    }
}
