package com.tinet.clink.openapi.response.cdr;

import com.tinet.clink.openapi.model.CdrObDetailModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * 查询外呼通话记录明细详情响应
 *
 * @author huwk
 * @date 2018/10/24
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DescribeCdrObDetailsResponse extends ResponseModel {

    /**
     * 通话记录明细详情列表
     */
    private List<CdrObDetailModel> cdrObDetails;

    public List<CdrObDetailModel> getCdrObDetails() {
        return cdrObDetails;
    }

    public void setCdrObDetails(List<CdrObDetailModel> cdrObDetails) {
        this.cdrObDetails = cdrObDetails;
    }
}
