package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.StatQueuePeriodListResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 在线客服-队列报表_按时间统计 Request
 *
 * @author midong
 * @date 2024/11/11
 */
public class StatQueuePeriodListRequest extends AbstractStatRequest<StatQueuePeriodListResponse> {

    private String startTime;

    private String endTime;

    @Deprecated
    private List<Integer> appType;

    private List<Integer> contactTypes;

    private List<String> qnos;

    private Integer periodType;

    public Integer getPeriodType() {
        return periodType;
    }

    public void setPeriodType(Integer periodType) {
        this.periodType = periodType;
        if (periodType != null) {
            putQueryParameter("periodType", periodType);
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

    public List<Integer> getContactTypes() {
        return contactTypes;
    }

    public void setContactTypes(List<Integer> contactTypes) {
        this.contactTypes = contactTypes;
        if (contactTypes != null && contactTypes.size() != 0) {
            putQueryParameter("contactTypes", convertIntegerListToString(contactTypes));
        }
    }

    public List<Integer> getAppType() {
        return appType;
    }

    public void setAppType(List<Integer> appType) {
        this.appType = appType;
        if (appType != null && appType.size() != 0) {
            putQueryParameter("appType", convertIntegerListToString(appType));
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

    public List<String> getQnos() {
        return qnos;
    }

    public void setQnos(List<String> qnos) {
        this.qnos = qnos;
        if (qnos != null && qnos.size() != 0) {
            putQueryParameter("qnos", convertStringListToString(qnos));
        }
    }

    @Override
    public Class<StatQueuePeriodListResponse> getResponseClass() {
        return StatQueuePeriodListResponse.class;
    }

    public StatQueuePeriodListRequest() {
        super(PathEnum.statQueuePeriodList.value(), HttpMethodType.POST);
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
