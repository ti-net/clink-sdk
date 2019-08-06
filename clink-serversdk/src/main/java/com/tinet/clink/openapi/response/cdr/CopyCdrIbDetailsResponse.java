package com.tinet.clink.openapi.response.cdr;

import com.tinet.clink.openapi.model.CdrIbDetailModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 数据同步呼入通话记录明细响应
 *
 * @author huwk
 * @date 2018/10/24
 **/
public class CopyCdrIbDetailsResponse extends PagedResponse {

    /**
     * 游标 id
     */
    private String scrollId;

    /**
     * 通话记录详情列表
     */
    private List<CdrIbDetailModel> cdrIbDetails;

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public List<CdrIbDetailModel> getCdrIbDetails() {
        return cdrIbDetails;
    }

    public void setCdrIbDetails(List<CdrIbDetailModel> cdrIbDetails) {
        this.cdrIbDetails = cdrIbDetails;
    }
}
