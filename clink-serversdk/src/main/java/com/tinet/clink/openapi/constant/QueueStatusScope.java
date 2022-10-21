package com.tinet.clink.openapi.constant;

/**
 * Class for:
 * 队列监控监控范围
 *
 * @author yinzk
 * @date 2022/7/7
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  QueueStatusScope {
    /**
     * 全部
     */
    public static final String STATUS_SCOPE_ALL = "all";
    /**
     * 队列参数
     */
    public static final String STATUS_SCOPE_QUEUE_PARAMS = "queueParams";
    /**
     * 队列成员
     */
    public static final String STATUS_SCOPE_AGENT_STATUS = "agentStatus";
    /**
     * 排队成员
     */
    public static final String STATUS_SCOPE_QUEUE_ENTRIES = "queueEntries";
}
