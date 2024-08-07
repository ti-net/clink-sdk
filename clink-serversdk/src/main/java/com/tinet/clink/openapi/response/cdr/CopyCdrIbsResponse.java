package com.tinet.clink.openapi.response.cdr;

import com.tinet.clink.openapi.model.CdrIbRecordDetailModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 数据同步呼入通话记录响应
 *
 * @author huwk
 * @date 2018/10/24
 **/
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  CopyCdrIbsResponse extends PagedResponse {

    /**
     * 游标 id
     */
    private String scrollId;

    /**
     * 主通话记录列表
     */
    private List<CdrIbRecordDetailModel> cdrIbs;

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public List<CdrIbRecordDetailModel> getCdrIbs() {
        return cdrIbs;
    }

    public void setCdrIbs(List<CdrIbRecordDetailModel> cdrIbs) {
        this.cdrIbs = cdrIbs;
    }
}
