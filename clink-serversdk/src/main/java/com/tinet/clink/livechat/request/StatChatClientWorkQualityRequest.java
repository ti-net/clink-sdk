package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.response.StatChatClientWorkQualityResponse;
import com.tinet.clink.ticket.request.stat.AbstractStatRequest;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 在线客服-座席工作考勤报表 Request
 * @author midong
 */
public class StatChatClientWorkQualityRequest extends AbstractStatRequest<StatChatClientWorkQualityResponse> {

    /**
     * 起始日期
     */
    private String startTime;

    /**
     * 截止日期
     */
    private String endTime;

    /**
     * 默认正序
     */
    private boolean sortAsc;

    /**
     * 座席数组
     */
    private List<String> cnos;

    /**
     *  渠道类型
     */
    private List<Integer> appType;


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

    public Boolean getSortAsc() {
        return sortAsc;
    }

    public void setSortAsc(Boolean sortAsc) {
        this.sortAsc = sortAsc;
        if (sortAsc != null) {
            putQueryParameter("sortAsc", sortAsc);
        }
    }

    public List<String> getCnos() {
        return cnos;
    }

    public void setCnos(List<String> cnos) {
        this.cnos = cnos;
        if (cnos != null) {
            putQueryParameter("cnos", convertStringListToString(cnos));
            }
        }


    public List<Integer> getAppType() {
        return appType;
    }

    public void setAppType(List<Integer> appType) {
        this.appType = appType;
        if (appType != null) {
            putQueryParameter("appType", convertIntegerListToString(appType));
        }

    }
    @Override
    public Class<StatChatClientWorkQualityResponse> getResponseClass() {
        return StatChatClientWorkQualityResponse.class;
    }

    public StatChatClientWorkQualityRequest() {
        super(PathEnum.StatChatClientWorkQuality.value(), HttpMethodType.POST);
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
