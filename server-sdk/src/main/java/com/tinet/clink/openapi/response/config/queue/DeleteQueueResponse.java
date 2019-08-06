package com.tinet.clink.openapi.response.config.queue;

import com.tinet.clink.openapi.response.ResponseModel;

/**
 *
 * @author lizy
 * @date 2018/10/25
 */
public class DeleteQueueResponse extends ResponseModel {

    private String qno;

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
    }
}
