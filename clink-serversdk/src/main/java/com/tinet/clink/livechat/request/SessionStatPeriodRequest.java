package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.SessionStatPeriodResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 在线客服 - 会话量报表_按会话量号统计
 *
 * @author wangbin
 * @since 2024/12/10
 */
public class SessionStatPeriodRequest extends AbstractStatRequest<SessionStatPeriodResponse> {


    private String startTime;

    private String endTime;

    private List<Integer> appType;

    private Integer periodType;

    public Integer getPeriodType() {
        return periodType;
    }

    public void setPeriodType(Integer periodType) {
        this.periodType = periodType;
        if (periodType != null) {
            putBodyParameter("periodType", periodType);
        }
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
        if (startTime != null) {
            putQueryParameter("startTime", startTime);
        }
    }

    public List<Integer> getAppType() {
        return appType;
    }


    public void setAppType(List<Integer> appType) {
        this.appType = appType;
        if (appType != null && appType.size() != 0) {
            putQueryParameter("appTypes", convertIntegerListToString(appType));
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

    @Override
    public Class<SessionStatPeriodResponse> getResponseClass() {
        return SessionStatPeriodResponse.class;
    }

    public SessionStatPeriodRequest() {
        super(PathEnum.StatSessionPeriodList.value(), HttpMethodType.POST);
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
