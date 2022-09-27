package com.tinet.clink.openapi.response.config.queue;

import com.tinet.clink.openapi.response.ResponseModel;

/**
 *
 * @author lizy
 * @date 2018/10/25
 */
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  DeleteQueueResponse extends ResponseModel {

    private String qno;

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
    }
}
