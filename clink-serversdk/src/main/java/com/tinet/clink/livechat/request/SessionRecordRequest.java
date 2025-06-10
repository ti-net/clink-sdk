package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.SessionRecordResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 同步会话记录
 *
 * @author wangbin
 * @since 2024/12/10
 */
public class SessionRecordRequest extends AbstractStatRequest<SessionRecordResponse> {

    private String startTime;

    private String endTime;

    private Integer pageSize;

    private Integer pageIndex;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
        if (startTime != null) {
            putQueryParameter("startTime", startTime);
        }
    }


    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
        if (endTime != null) {
            putQueryParameter("endTime", endTime);
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

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
        if (pageIndex != null) {
            putQueryParameter("pageIndex", pageIndex);
        }
    }

    public SessionRecordRequest() {
        super(PathEnum.RecordSessionPage.value(), HttpMethodType.POST);
    }

    private static String convertStringListToString(List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        } else {
            return list.stream().collect(Collectors.joining(","));
        }
    }

    private static String convertIntegerListToString(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return null;
        } else {
            return list.stream().map(Object::toString).collect(Collectors.joining(","));
        }
    }

    @Override
    public Class<SessionRecordResponse> getResponseClass() {
        return SessionRecordResponse.class;
    }
}
