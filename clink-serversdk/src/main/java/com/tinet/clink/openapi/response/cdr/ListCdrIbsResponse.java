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
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListCdrIbsResponse extends PagedResponse {

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
