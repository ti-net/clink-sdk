package com.tinet.clink.cc.request.sms;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.client.ListClientsResponse;
import com.tinet.clink.cc.response.sms.ListSmsTemplateResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * 短信模板列表查询请求
 *
 * @Author liyj
 * @Date 2023-06-27 15:41
 **/
public class ListSmsTemplateRequest extends AbstractRequestModel<ListSmsTemplateResponse> {

    /**
     * 状态：
     *   1：审核中
     *   2：审核通过
     *   3：审核未通过
     */
    private Integer status;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 创建时间查询范围-开始时间,秒级时间戳
     */
    private Long startTime;

    /**
     * 创建时间查询范围-结束时间,秒级时间戳
     */
    private Long endTime;

    /**
     * 偏移量，默认值为 0，最大范围 10000
     */
    private Integer offset;

    /**
     * 查询记录条数，默认值为 10，最大范围 100
     */
    private Integer limit;

    @Override
    public Class<ListSmsTemplateResponse> getResponseClass() {
        return ListSmsTemplateResponse.class;
    }

    public ListSmsTemplateRequest() {
        super(PathEnum.ListSmsTemplates.value(), HttpMethodType.GET);
    }

    public Integer getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setStatus(Integer status) {
        this.status = status;
        if (status != null) {
            putQueryParameter("status", status);
        }
    }

    public void setName(String name) {
        this.name = name;
        if (name != null) {
            putQueryParameter("name", name);
        }
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
        if (startTime != null) {
            putQueryParameter("startTime", startTime);
        }
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
        if (endTime != null) {
            putQueryParameter("endTime", endTime);
        }
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (offset != null) {
            putQueryParameter("offset", offset);
        }
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (limit != null) {
            putQueryParameter("limit", limit);
        }
    }
}
