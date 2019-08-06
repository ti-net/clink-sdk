package com.tinet.clink.openapi.response.config.client;

import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 删除座席响应
 *
 * @author lizy
 * @date 2018/10/24
 */
public class DeleteClientResponse extends ResponseModel {

    private String cno;


    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }
}
