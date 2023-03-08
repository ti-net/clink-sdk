package com.tinet.clink.ticket.response;

import com.tinet.clink.core.response.ResponseModel;

/**
 * @author wangli
 * @date 2023-02-23 21:06
 */
public class TicketRecordFilesUrlResponse extends ResponseModel {

    /**
     * 工单记录文件链接
     */
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
