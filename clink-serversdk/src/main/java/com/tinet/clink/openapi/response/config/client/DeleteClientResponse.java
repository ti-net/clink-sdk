package com.tinet.clink.openapi.response.config.client;

import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 删除座席响应
 *
 * @author lizy
 * @date 2018/10/24
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DeleteClientResponse extends ResponseModel {

    private String cno;


    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }
}
