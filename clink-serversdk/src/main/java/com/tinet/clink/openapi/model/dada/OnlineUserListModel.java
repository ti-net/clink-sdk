package com.tinet.clink.openapi.model.dada;


/**
 *
 * @author liuhy
 * @date: 2022/3/17
 **/
public class OnlineUserListModel {

    private String userId;

    private String userName;

    private String cno;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }
}