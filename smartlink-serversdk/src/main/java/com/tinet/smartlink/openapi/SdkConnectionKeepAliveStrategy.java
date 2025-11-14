package com.tinet.smartlink.openapi;

import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;

/**
 * SDK keep alive 时间策略，默认为5 分钟
 * @author houfc
 * @date 2019/1/8
 */
public class SdkConnectionKeepAliveStrategy implements ConnectionKeepAliveStrategy {

    /**
     * 默认为 5分钟
     */
    private long keepAliveDurationMillis = SmartlinkClientConfiguration.KEEP_ALIVE_DURATION_MILLIS;

    public SdkConnectionKeepAliveStrategy(long keepAliveDurationMillis) {
        this.keepAliveDurationMillis = keepAliveDurationMillis;
    }

    @Override
    public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
        return keepAliveDurationMillis;
    }
}
