package com.tinet.clink.openapi.response;

import com.tinet.clink.openapi.model.User;

/**
 * @author houfc
 */
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  Demo1Response extends ResponseModel {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
