package com.tinet.clink.cc.response.queue;

import com.tinet.clink.core.response.ResponseModel;

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
