package com.tinet.clink.ticket.response;

import com.tinet.clink.core.response.ResponseModel;

/**
 * @author wangli
 * @date 2023-02-23 20:46
 */
public class TicketRecordFilesGenerateResponse extends ResponseModel {

    /**
     * 生成结果 1：成功  0：失败
     */
    private Integer result;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

}
