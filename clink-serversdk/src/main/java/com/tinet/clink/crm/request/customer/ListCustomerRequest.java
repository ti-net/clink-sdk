package com.tinet.clink.crm.request.customer;

import com.tinet.clink.crm.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.crm.response.customer.ListCustomerResponse;

/**
 * 获取客户资料请求
 *
 * @author jiangyang
 * @date 2019/11/12
 */
public class ListCustomerRequest extends AbstractRequestModel<ListCustomerResponse> {

    /**
     * 可用的查询参数,Map格式的字符串
     */
    private String customerParams;

    /**
     * 创建时间查询条件-开始时间
     */
    private Long startTime;

    /**
     * 创建时间查询条件-结束时间
     */
    private Long endTime;

    /**
     * 按修改时间查询的起始时间
     */
    private Long updateStartTime;

    /**
     * 按修改时间查询的结束时间
     */
    private Long updateEndTime;

    /**
     * 偏移量，范围 0-10000，默认值为 0
     */
    private Integer offset;

    /**
     * 查询条数，范围 10-100，默认值为 10
     */
    private Integer limit;

    public ListCustomerRequest() {
        super(PathEnum.ListCustomers.value(), HttpMethodType.GET);
    }

    @Override
    public Class getResponseClass() {
        return ListCustomerResponse.class;
    }

    public String getCustomerParams() {
        return customerParams;
    }

    public void setCustomerParams(String customerParams) {
        this.customerParams = customerParams;
        if (customerParams != null) {
            putQueryParameter("customerParams", customerParams);
        }
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (offset != null) {
            putQueryParameter("offset", offset);
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (limit != null) {
            putQueryParameter("limit", limit);
        }
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
        if (startTime != null) {
            putQueryParameter("startTime", startTime);
        }
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
        if (endTime != null) {
            putQueryParameter("endTime", endTime);
        }
    }

    public Long getUpdateStartTime() {
        return updateStartTime;
    }

    public void setUpdateStartTime(Long updateStartTime) {
        this.updateStartTime = updateStartTime;
        if (updateStartTime != null) {
            putQueryParameter("updateStartTime", updateStartTime);
        }
    }

    public Long getUpdateEndTime() {
        return updateEndTime;
    }

    public void setUpdateEndTime(Long updateEndTime) {
        this.updateEndTime = updateEndTime;
        if (updateEndTime != null) {
            putQueryParameter("updateEndTime", updateEndTime);
        }
    }
}
