package com.tinet.clink.openapi.response;

import com.tinet.clink.openapi.model.User;

import java.util.List;

/**
 * @author houfc
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  Demo7Response extends PagedResponse {

    private List<User> users;


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

