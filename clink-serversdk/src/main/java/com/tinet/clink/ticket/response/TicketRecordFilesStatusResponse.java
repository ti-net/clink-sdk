package com.tinet.clink.ticket.response;

import com.tinet.clink.core.response.ResponseModel;

/**
 * @author wangli
 * @date 2023-02-23 21:01
 */
public class TicketRecordFilesStatusResponse extends ResponseModel {

    /**
     * 文件状态 1：已完成  0：未完成
     */
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
