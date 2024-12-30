package com.tinet.clink.livechat.request;


import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.ClientStatAttendancePageResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 在线客服-座席考勤报表（新）
 *
 * @author midong
 * @date 2024/11/11
 */
public class ClientStatAttendancePageRequest extends AbstractStatRequest<ClientStatAttendancePageResponse> {

    private String startTime;

    private String endTime;

    private List<String> cnos;

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

    public List<String> getCnos() {
        return cnos;
    }

    public void setCnos(List<String> cnos) {
        this.cnos = cnos;
        if (cnos != null && cnos.size() != 0) {
            putQueryParameter("cnos", convertStringListToString(cnos));
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
    public Class<ClientStatAttendancePageResponse> getResponseClass() {
        return ClientStatAttendancePageResponse.class;
    }

    public ClientStatAttendancePageRequest() {
        super(PathEnum.StatClientAttendanceCnoPage.value(), HttpMethodType.POST);
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
