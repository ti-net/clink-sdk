package com.tinet.clink.ticket;

/**
 * 用户参数类型
 */
public enum UserIdType {
    /**
     * 用户id
     */
    USER_ID(1),
    /**
     * 用户手机号
     */
    USER_TEL(2),
    /**
     * 用户工号
     */
    UNO(3),
    /**
     * 用户名
     */
    USER_NAME(4),
    /**
     * sso标识
     */
    SSO_ID(5),
    ;

    private int code;

    public int getCode() {
        return code;
    }

    UserIdType(int code) {
        this.code = code;
    }

    public static UserIdType getByCode(int code) {
        for (UserIdType value : UserIdType.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }
}
