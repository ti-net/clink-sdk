package com.tinet.clink.cc.response.cdr;



import com.tinet.clink.cc.model.CdrIbDetailModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * 查询呼入通话记录明细详情响应
 *
 * @author huwk
 * @date 2018/10/24
 **/
public class DescribeCdrIbDetailsResponse extends ResponseModel {

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
