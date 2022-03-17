package com.tinet.clink.openapi.request.log;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.log.ListAgentDurationLogsResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 查询座席工作时长日志列表请求
 *
 * @author wangli
 * @date 2022-03-10 5:28 PM
 */
public class ListAgentDurationLogsRequest extends AbstractRequestModel<ListAgentDurationLogsResponse> {

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

    public ListAgentDurationLogsRequest() {
        super(PathEnum.ListLogAgentDuration.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListAgentDurationLogsResponse> getResponseClass() {
        return ListAgentDurationLogsResponse.class;
    }
}
