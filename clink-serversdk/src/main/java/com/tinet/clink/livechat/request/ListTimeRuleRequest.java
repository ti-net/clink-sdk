package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.response.ListTimeRuleResponse;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;

/**
 * 只读时间条件列表
 */
public class ListTimeRuleRequest extends AbstractStatRequest<ListTimeRuleResponse> {

    /**
     * 1为星期，2为固定日期
     */
    private Integer type;

    /**
     * 页码，默认1
     */
    private Integer pageIndex;

    /**
     * 每页条数，默认20
     */
    private Integer pageSize;

    public ListTimeRuleRequest() {
        super(PathEnum.ListTimeRule.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ListTimeRuleResponse> getResponseClass() {
        return ListTimeRuleResponse.class;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
        if (type != null) {
            putQueryParameter("type", type);
        }
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
        if (pageIndex != null) {
            putQueryParameter("pageIndex", pageIndex);
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        if (pageSize != null) {
            putQueryParameter("pageSize", pageSize);
        }
    }

}
