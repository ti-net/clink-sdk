package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.StatChatClientAttendanceResponse;


/**
 * 在线客服-座席工作考勤报表 Request
 *
 * @author lj
 */
public class StatChatClientAttendanceRequest extends AbstractStatRequest<StatChatClientAttendanceResponse> {

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
    private String[] cnos;

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

    public String[] getCnos() {
        return cnos;
    }

    public void setCnos(String[] cnos) {
        this.cnos = cnos;
        if (cnos != null) {
            putQueryParameter("cnos", cnos);
        }

    }

    @Override
    public Class<StatChatClientAttendanceResponse> getResponseClass() {
        return StatChatClientAttendanceResponse.class;
    }

    public StatChatClientAttendanceRequest() {
        super(PathEnum.StatChatClientAttendance.value(), HttpMethodType.POST);
    }

}