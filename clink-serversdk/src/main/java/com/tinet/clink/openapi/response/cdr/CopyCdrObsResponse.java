package com.tinet.clink.openapi.response.cdr;

import com.tinet.clink.openapi.model.CdrObRecordDetailModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 数据同步外呼通话记录响应
 *
 * @author huwk
 * @date 2018/10/24
 **/
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  CopyCdrObsResponse extends PagedResponse {

    /**
     * 游标 id
     */
    private String scrollId;

    /**
     * 主通话记录列表
     */
    private List<CdrObRecordDetailModel> cdrObs;

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public List<CdrObRecordDetailModel> getCdrObs() {
        return cdrObs;
    }

    public void setCdrObs(List<CdrObRecordDetailModel> cdrObs) {
        this.cdrObs = cdrObs;
    }
}
