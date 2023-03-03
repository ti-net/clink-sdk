package com.tinet.clink.ticket.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.ticket.response.TicketRecordFilesGenerateResponse;

/**
 * 工单自定义导出-生成文件请求
 *
 * @author wangli
 * @date 2023-02-23 20:38
 */
public class TicketRecordFilesGenerateRequest extends AbstractRequestModel<TicketRecordFilesGenerateResponse> {

    private String date;

    private Integer type;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        if (date != null) {
            putQueryParameter("date", date);
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

    public TicketRecordFilesGenerateRequest() {
        super(PathEnum.TicketRecordFilesGenerate.value(), HttpMethodType.GET);
    }

    @Override
    public Class<TicketRecordFilesGenerateResponse> getResponseClass() {
        return TicketRecordFilesGenerateResponse.class;
    }

}
