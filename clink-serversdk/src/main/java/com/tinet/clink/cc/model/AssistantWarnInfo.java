package com.tinet.clink.cc.model;

/**
 * Class For:
 * 智能助手 - 预警信息
 *
 * @author Tinet-yinzk
 * @date 2024/3/19 15:16
 */
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class AssistantWarnInfo {
    /**
     * 告警类型
     * <p>
     * emotion：情绪告警
     * <p>
     * sensitive：敏感词告警
     * <p>
     * speechRate：语速告警
     * <p>
     * forestall：抢话告警
     * <p>
     * processQuality：流程质检
     */
    private String warnType;
    /**
     * 引起告警的内容
     */
    private String content;

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
