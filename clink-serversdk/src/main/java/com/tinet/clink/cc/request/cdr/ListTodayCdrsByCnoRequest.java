package com.tinet.clink.cc.request.cdr;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.cdr.ListTodayCdrsByCnoResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * 查询呼入通话记录列表请求
 *
 * @author huwk
 * @date 2018/10/23
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
     * 开始时间
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
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    @Override
    public Class<ListTodayCdrsByCnoResponse> getResponseClass() {
        return ListTodayCdrsByCnoResponse.class;
    }
}

