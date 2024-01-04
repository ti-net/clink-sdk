package com.tinet.clink.huanxin.model;

public class IMRegisterUserModel {

    /**
     * 用户ID
     */
    private String username;

    /**
     * 用户的登录密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    public IMRegisterUserModel(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}

