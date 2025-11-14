package com.tinet.clink.cc.model;

/**
 * Class For:
 * 语音流节点
 *
 * @author Tinet-yinzk
 * @date 2024/2/23 13:55
 */
public class CdrAudioFlowNodeModel {
    /**
     * 节点：1：录音，2：保持，3：静音
     */
    private Integer node;
    /**
     * 节点类型，0：开始，1：结束
     */
    private Integer type;
    /**
     * 时间戳，单位：秒
     */
    private Long timestamp;

    public Integer getNode() {
        return node;
    }

    public void setNode(Integer node) {
        this.node = node;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
