package com.tinet.clink.openapi.response.cdr;

import com.tinet.clink.openapi.model.CdrInvestigationModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 查询满意度调查记录列表响应
 *
 * @author huwk
 * @date 2018/10/24
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListInvestigationsResponse extends PagedResponse {

    /**
     * 满意度调查列表
     */
    private List<CdrInvestigationModel> cdrInvestigations;

    public List<CdrInvestigationModel> getCdrInvestigations() {
        return cdrInvestigations;
    }

    public void setCdrInvestigations(List<CdrInvestigationModel> cdrInvestigations) {
        this.cdrInvestigations = cdrInvestigations;
    }
}
