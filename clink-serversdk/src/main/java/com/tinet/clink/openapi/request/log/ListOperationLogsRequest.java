package com.tinet.clink.openapi.request.log;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.log.ListOperationLogsResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 查询座席操作日志列表请求
 *
 * @author wangli
 * @date 2022-03-09 4:59 PM
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListOperationLogsRequest extends AbstractRequestModel<ListOperationLogsResponse> {

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 操作开始时间
     */
    private Long startTime;

    /**
     * 操作结束时间
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

    public ListOperationLogsRequest() {
        super(PathEnum.ListLogOperation.value(), HttpMethodType.GET);
    }


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

    @Override
    public Class<ListOperationLogsResponse> getResponseClass() {
        return ListOperationLogsResponse.class;
    }
}
