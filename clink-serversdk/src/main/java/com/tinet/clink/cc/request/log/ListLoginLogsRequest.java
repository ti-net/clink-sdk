package com.tinet.clink.cc.request.log;


import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.log.ListLoginLogsResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 查询登录日志列表请求
 *
 * @author wangli
 * @date 2022-03-10 5:36 PM
 */
public class ListLoginLogsRequest extends AbstractRequestModel<ListLoginLogsResponse> {

    /**
     * 用户类型 管理员 座席
     */
    private String userType;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 偏移量
     */
    private Integer offset;

    /**
     * 查询条数
     */
    private Integer limit;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
        if (userType != null) {
            putQueryParameter("userType", userType);
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

    public ListLoginLogsRequest() {
        super(PathEnum.ListLogLogin.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListLoginLogsResponse> getResponseClass() {
        return ListLoginLogsResponse.class;
    }

}
