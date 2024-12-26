package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.SessionStatAppResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 会话量报表_按接入号统计
 *
 * @author wangbin
 * @since 2024/12/10
 */
public class SessionStatAppRequest extends AbstractStatRequest<SessionStatAppResponse> {


    private String startTime;

    private String endTime;

    private List<Integer> contactTypes;

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

    public List<Integer> getContactTypes() {
        return contactTypes;
    }


    public void setContactTypes(List<Integer> contactTypes) {
        this.contactTypes = contactTypes;
        if (contactTypes != null && contactTypes.size() != 0) {
            putQueryParameter("contactTypes", convertIntegerListToString(contactTypes));
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

    @Override
    public Class<SessionStatAppResponse> getResponseClass() {
        return SessionStatAppResponse.class;
    }

    public SessionStatAppRequest() {
        super(PathEnum.StatSessionAppPage.value(), HttpMethodType.POST);
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

}
