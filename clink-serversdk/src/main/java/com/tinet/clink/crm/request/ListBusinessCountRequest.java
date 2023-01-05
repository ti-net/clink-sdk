package com.tinet.clink.crm.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.crm.PathEnum;
import com.tinet.clink.crm.response.ListBusinessCountResponse;
import com.tinet.clink.crm.response.ListBusinessResponse;

/**
 * 业务记录查询
 *
 * @author liuhy
 * @date: 2020/7/29
 **/
public class ListBusinessCountRequest extends AbstractRequestModel<ListBusinessCountResponse> {


    private Long startTime;

    private Long endTime;

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


    public ListBusinessCountRequest() {
        super(PathEnum.ListBusinessCount.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ListBusinessCountResponse> getResponseClass() {
        return ListBusinessCountResponse.class;
    }


}