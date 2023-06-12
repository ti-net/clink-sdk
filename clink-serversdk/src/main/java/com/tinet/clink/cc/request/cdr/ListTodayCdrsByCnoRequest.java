package com.tinet.clink.cc.request.cdr;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.cdr.ListTodayCdrsByCnoResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

import java.util.Objects;

/**
 * 查询座席今日通话列表
 *
 * @author yinzk
 * @date 2023/6/9
 **/
public class ListTodayCdrsByCnoRequest extends AbstractRequestModel<ListTodayCdrsByCnoResponse> {

    /**
     * 座席号，要求只能是 4-6 位数字
     */
    private String cno;
    /**
     * 记录条数，范围 1-100
     */
    private Integer limit;
    /**
     * 最新记录开始时间对应的时间戳，单位：s，默认值取当前时间戳
     */
    private Long startTime;

    public ListTodayCdrsByCnoRequest() {
        super(PathEnum.ListTodayCdrsByCno.value(), HttpMethodType.GET);
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (Objects.nonNull(cno) && cno.length() > 0) {
            this.putQueryParameter("cno", cno);
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (Objects.nonNull(limit)) {
            this.putQueryParameter("limit", limit);
        }
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
        if (Objects.nonNull(startTime)) {
            this.putQueryParameter("startTime", startTime);
        }
    }

    @Override
    public Class<ListTodayCdrsByCnoResponse> getResponseClass() {
        return ListTodayCdrsByCnoResponse.class;
    }
}

