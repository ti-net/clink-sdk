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

    /**
     * 最近一次重复推广时间查询范围-开始时间,秒级时间戳
     */
    private Long lastRepeatPromoteStartTime;
    /**
     * 最近一次重复推广时间查询范围-结束时间,秒级时间戳
     */
    private Long lastRepeatPromoteEndTime;
    /**
     * 回收时间查询范围-开始时间,秒级时间戳
     */
    private Long retrieveStartTime;
    /**
     * 回收时间查询范围-结束时间,秒级时间戳
     */
    private Long retrieveEndTime;
    /**
     * 分配时间查询范围-开始时间,秒级时间戳
     */
    private Long assignStartTime;
    /**
     * 分配时间查询范围-结束时间,秒级时间戳
     */
    private Long assignEndTime;
    /**
     * 最后一次联系时间查询范围-开始时间,秒级时间戳
     */
    private Long lastContactStartTime;
    /**
     * 最后一次联系时间查询范围-结束时间,秒级时间戳
     */
    private Long lastContactEndTime;

    /**
     * 首次联系时间查询范围-开始时间,秒级时间戳
     */
    private Long firstContactStartTime;

    /**
     * 首次联系时间查询范围-结束时间,秒级时间戳
     */
    private Long firstContactEndTime;

    /**
     * 首次电话联系时间查询范围-开始时间,秒级时间戳
     */
    private Long firstCcContactStartTime;

    /**
     * 首次电话联系时间查询范围-结束时间,秒级时间戳
     */
    private Long firstCcContactEndTime;


    /**
     * 首次在线客服联系时间查询范围-开始时间,秒级时间戳
     */
    private Long firstChatContactStartTime;

    /**
     * 首次在线客服联系时间查询范围-结束时间,秒级时间戳
     */
    private Long firstChatContactEndTime;


    /**
     * 最后电话联系时间查询范围-开始时间,秒级时间戳
     */
    private Long lastCcContactStartTime;

    /**
     * 最后电话联系时间查询范围-结束时间,秒级时间戳
     */
    private Long lastCcContactEndTime;


    /**
     * 最后在线客服联系时间查询范围-开始时间,秒级时间戳
     */
    private Long lastChatContactStartTime;

    /**
     * 最后在线客服联系时间查询范围-结束时间,秒级时间戳
     */
    private Long lastChatContactEndTime;

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

    public Long getLastRepeatPromoteStartTime() {
        return lastRepeatPromoteStartTime;
    }

    public void setLastRepeatPromoteStartTime(Long lastRepeatPromoteStartTime) {
        this.lastRepeatPromoteStartTime = lastRepeatPromoteStartTime;
        if (lastRepeatPromoteStartTime != null) {
            putQueryParameter("lastRepeatPromoteStartTime", lastRepeatPromoteStartTime);
        }
    }

    public Long getLastRepeatPromoteEndTime() {
        return lastRepeatPromoteEndTime;
    }

    public void setLastRepeatPromoteEndTime(Long lastRepeatPromoteEndTime) {
        this.lastRepeatPromoteEndTime = lastRepeatPromoteEndTime;
        if (lastRepeatPromoteEndTime != null) {
            putQueryParameter("lastRepeatPromoteEndTime", lastRepeatPromoteEndTime);
        }
    }

    public Long getRetrieveStartTime() {
        return retrieveStartTime;
    }

    public void setRetrieveStartTime(Long retrieveStartTime) {
        this.retrieveStartTime = retrieveStartTime;
        if (retrieveStartTime != null) {
            putQueryParameter("retrieveStartTime", retrieveStartTime);
        }
    }

    public Long getRetrieveEndTime() {
        return retrieveEndTime;
    }

    public void setRetrieveEndTime(Long retrieveEndTime) {
        this.retrieveEndTime = retrieveEndTime;
        if (retrieveEndTime != null) {
            putQueryParameter("retrieveEndTime", retrieveEndTime);
        }
    }

    public Long getAssignStartTime() {
        return assignStartTime;
    }

    public void setAssignStartTime(Long assignStartTime) {
        this.assignStartTime = assignStartTime;
        if (assignStartTime != null) {
            putQueryParameter("assignStartTime", assignStartTime);
        }
    }

    public Long getAssignEndTime() {
        return assignEndTime;
    }

    public void setAssignEndTime(Long assignEndTime) {
        this.assignEndTime = assignEndTime;
        if (assignEndTime != null) {
            putQueryParameter("assignEndTime", assignEndTime);
        }
    }

    public Long getLastContactStartTime() {
        return lastContactStartTime;
    }

    public void setLastContactStartTime(Long lastContactStartTime) {
        this.lastContactStartTime = lastContactStartTime;
        if (lastContactStartTime != null) {
            putQueryParameter("lastContactStartTime", lastContactStartTime);
        }
    }

    public Long getLastContactEndTime() {
        return lastContactEndTime;
    }

    public void setLastContactEndTime(Long lastContactEndTime) {
        this.lastContactEndTime = lastContactEndTime;
        if (lastContactEndTime != null) {
            putQueryParameter("lastContactEndTime", lastContactEndTime);
        }
    }

    public Long getFirstContactStartTime() {
        return firstContactStartTime;
    }




    public void setFirstContactStartTime(Long firstContactStartTime) {
        this.firstContactStartTime = firstContactStartTime;
        if (firstContactStartTime != null) {
            putQueryParameter("firstContactStartTime", firstContactStartTime);
        }
    }

    public Long getFirstContactEndTime() {
        return firstContactEndTime;
    }

    public void setFirstContactEndTime(Long firstContactEndTime) {
        this.firstContactEndTime = firstContactEndTime;
        if (firstContactEndTime != null) {
            putQueryParameter("firstContactEndTime", firstContactEndTime);
        }
    }

    public Long getFirstCcContactStartTime() {
        return firstCcContactStartTime;
    }

    public void setFirstCcContactStartTime(Long firstCcContactStartTime) {
        this.firstCcContactStartTime = firstCcContactStartTime;
        if (firstCcContactStartTime != null) {
            putQueryParameter("firstCcContactStartTime", firstCcContactStartTime);
        }
    }

    public Long getFirstCcContactEndTime() {
        return firstCcContactEndTime;
    }

    public void setFirstCcContactEndTime(Long firstCcContactEndTime) {
        this.firstCcContactEndTime = firstCcContactEndTime;
        if (firstCcContactEndTime != null) {
            putQueryParameter("firstCcContactEndTime", firstCcContactEndTime);
        }
    }

    public Long getFirstChatContactStartTime() {
        return firstChatContactStartTime;
    }

    public void setFirstChatContactStartTime(Long firstChatContactStartTime) {
        this.firstChatContactStartTime = firstChatContactStartTime;
        if (firstChatContactStartTime != null) {
            putQueryParameter("firstChatContactStartTime", firstChatContactStartTime);
        }
    }

    public Long getFirstChatContactEndTime() {
        return firstChatContactEndTime;
    }

    public void setFirstChatContactEndTime(Long firstChatContactEndTime) {
        this.firstChatContactEndTime = firstChatContactEndTime;
        if (firstChatContactEndTime != null) {
            putQueryParameter("firstChatContactEndTime", firstChatContactEndTime);
        }
    }

    public Long getLastCcContactStartTime() {
        return lastCcContactStartTime;
    }

    public void setLastCcContactStartTime(Long lastCcContactStartTime) {
        this.lastCcContactStartTime = lastCcContactStartTime;
        if (lastCcContactStartTime != null) {
            putQueryParameter("lastCcContactStartTime", lastCcContactStartTime);
        }
    }

    public Long getLastCcContactEndTime() {
        return lastCcContactEndTime;
    }

    public void setLastCcContactEndTime(Long lastCcContactEndTime) {
        this.lastCcContactEndTime = lastCcContactEndTime;
        if (lastCcContactEndTime != null) {
            putQueryParameter("lastCcContactEndTime", lastCcContactEndTime);
        }
    }

    public Long getLastChatContactStartTime() {
        return lastChatContactStartTime;
    }

    public void setLastChatContactStartTime(Long lastChatContactStartTime) {
        this.lastChatContactStartTime = lastChatContactStartTime;
        if (lastChatContactStartTime != null) {
            putQueryParameter("lastChatContactStartTime", lastChatContactStartTime);
        }
    }

    public Long getLastChatContactEndTime() {
        return lastChatContactEndTime;
    }

    public void setLastChatContactEndTime(Long lastChatContactEndTime) {
        this.lastChatContactEndTime = lastChatContactEndTime;
        if (lastChatContactEndTime != null) {
            putQueryParameter("lastChatContactEndTime", lastChatContactEndTime);
        }
    }
}
