package com.tinet.smartlink.openapi.request.sqc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.ScrollResponse;
import lombok.Getter;

import java.util.Date;

/**
 * 查询话单列表
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CdrScrollRequest extends BaseRequest<ScrollResponse> {

    /**
     * 分页用的游标，首次传null。后续传上一次调用返回的cursor
     */
    private String cursor;

    /**
     * 查询条数，最大值为100
     */
    private Integer limit = 10;

    /**
     * 查询开始时间
     */
    private Date channelTimeBegin;

    /**
     * 查询结束时间
     */
    private Date channelTimeEnd;

    /**
     * 质检结果更新开始时间
     */
    private Date qcUpdateTimeBegin;

    /**
     * 质检结果更新结束时间
     */
    private Date qcUpdateTimeEnd;

    public void setCursor(String cursor) {
        this.cursor = cursor;
        putBodyParameter("cursor", cursor);
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        putBodyParameter("limit", limit);
    }

    public void setChannelTimeBegin(Date channelTimeBegin) {
        this.channelTimeBegin = channelTimeBegin;
        putBodyParameter("channelTimeBegin", channelTimeBegin);
    }

    public void setChannelTimeEnd(Date channelTimeEnd) {
        this.channelTimeEnd = channelTimeEnd;
        putBodyParameter("channelTimeEnd", channelTimeEnd);
    }

    public void setQcUpdateTimeBegin(Date qcUpdateTimeBegin) {
        this.qcUpdateTimeBegin = qcUpdateTimeBegin;
        if (qcUpdateTimeBegin != null) {
            putBodyParameter("qcUpdateTimeBegin", qcUpdateTimeBegin);
        }
    }

    public void setQcUpdateTimeEnd(Date qcUpdateTimeEnd) {
        this.qcUpdateTimeEnd = qcUpdateTimeEnd;
        if (qcUpdateTimeEnd != null) {
            putBodyParameter("qcUpdateTimeEnd", qcUpdateTimeEnd);
        }
    }

    public CdrScrollRequest() {
        super("/sqc/scroll", HttpMethodType.POST);
    }

    @Override
    public Class<ScrollResponse> getResponseClass() {
        return ScrollResponse.class;
    }

}
