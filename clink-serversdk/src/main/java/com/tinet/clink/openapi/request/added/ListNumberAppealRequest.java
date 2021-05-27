package com.tinet.clink.openapi.request.added;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.added.ListNumberAppealResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * Class for:
 * 号码射弩记录查询请求
 *
 * @author yinzk
 * @date 2021/5/27
 */
public class ListNumberAppealRequest extends AbstractRequestModel<ListNumberAppealResponse> {

    /**
     * 客户号码
     */
    private String customerNumber;
    /**
     * 申诉状态 1：申诉通过，2：打回，3：未审核
     */
    private Integer appealStatus;
    /**
     * 申诉理由 （成交客户、客户回访、服务通知、成交意向客户、内部或客户伙伴沟通、其他）
     */
    private String appealReason;
    /**
     * 申诉时间 的 开始时间，格式: "2021-01-01 01:01:01"
     */
    private String startTime;
    /**
     * 申诉时间 的 结束时间，格式: "2021-01-01 01:01:01"
     */
    private String endTime;
    /**
     * 偏移量，范围 0-99990。默认值为 0。注：limit + offset 不允许超过100000
     */
    private Integer offset;
    /**
     * 查询条数，范围 10-100。默认值为 10。注：limit + offset 不允许超过100000
     */
    private Integer limit;

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
        putQueryParameter("customerNumber", customerNumber);

    }

    public void setAppealStatus(Integer appealStatus) {
        this.appealStatus = appealStatus;
        putQueryParameter("appealStatus", appealStatus);

    }

    public void setAppealReason(String appealReason) {
        this.appealReason = appealReason;
        putQueryParameter("appealReason", appealReason);
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
        putQueryParameter("startTime", startTime);
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
        putQueryParameter("endTime", endTime);
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        putQueryParameter("offset", offset);
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        putQueryParameter("limit", limit);
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public Integer getAppealStatus() {
        return appealStatus;
    }

    public String getAppealReason() {
        return appealReason;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public ListNumberAppealRequest() {
        super(PathEnum.ListNumberAppeal.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ListNumberAppealResponse> getResponseClass() {
        return ListNumberAppealResponse.class;
    }
}
