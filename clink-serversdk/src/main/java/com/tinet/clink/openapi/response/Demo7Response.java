package com.tinet.clink.openapi.response;

import com.tinet.clink.openapi.model.User;

import java.util.List;

/**
 * @author houfc
 */
public class Demo7Response extends PagedResponse {

    private List<User> users;


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

