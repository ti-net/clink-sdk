package com.tinet.clink.ticket.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.ticket.response.TicketRecordFilesUrlResponse;

/**
 * 工单自定义导出-获取文件链接请求
 *
 * @author wangli
 * @date 2023-02-23 21:04
 */
public class TicketRecordFilesUrlRequest extends AbstractRequestModel<TicketRecordFilesUrlResponse> {

    private String startDate;

    private String endDate;

    private Integer type;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
        if (startDate != null) {
            putQueryParameter("startDate", startDate);
        }
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
        if (endDate != null) {
            putQueryParameter("endDate", endDate);
        }
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
        if (type != null) {
            putQueryParameter("type", type);
        }
    }

    public TicketRecordFilesUrlRequest() {
        super(PathEnum.TicketRecordFileUrl.value(), HttpMethodType.GET);
    }

    @Override
    public Class<TicketRecordFilesUrlResponse> getResponseClass() {
        return TicketRecordFilesUrlResponse.class;
    }

}
