package com.tinet.smartlink.openapi.exceptions;

/**
 * @author houfc
 * @date 2018/11/27
 */
public enum ErrorType {
    // 客户端异常
    Client,

    // 服务端异常
    Server,

    // 限流
    Throttling,

    // 未知
    Unknown,
}
