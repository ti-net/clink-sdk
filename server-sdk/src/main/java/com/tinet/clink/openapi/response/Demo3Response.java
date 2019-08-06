package com.tinet.clink.openapi.response;

import com.tinet.clink.openapi.model.User;

/**
 * @author houfc
 */
public class Demo3Response extends ResponseModel {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
