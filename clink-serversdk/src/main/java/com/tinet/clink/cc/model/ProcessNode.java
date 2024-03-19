package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Class For:
 * 智能助手 - 流程话术节点
 *
 * @author Tinet-yinzk
 * @date 2024/3/19 15:16
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessNode {
    /**
     * 节点id
     */
    private String uuid;
    /**
     * 节点名称
     */
    private String name;
    /**
     * 节点状态，0：未命中，1：命中
     */
    private Integer status;
    /**
     * 会话是否到了当前节点，（是否需要高亮该节点）
     */
    private Integer isCurrentNode;
    /**
     * 推荐话术
     */
    private List<String> remindWords;
    /**
     * 父节点id
     */
    private String parentUuid;
    /**
     * 同级上一个节点
     */
    private ProcessNode previousMode;
    /**
     * 统计下一个节点
     */
    private ProcessNode nextMode;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsCurrentNode() {
        return isCurrentNode;
    }

    public void setIsCurrentNode(Integer isCurrentNode) {
        this.isCurrentNode = isCurrentNode;
    }

    public List<String> getRemindWords() {
        return remindWords;
    }

    public void setRemindWords(List<String> remindWords) {
        this.remindWords = remindWords;
    }

    public String getParentUuid() {
        return parentUuid;
    }

    public void setParentUuid(String parentUuid) {
        this.parentUuid = parentUuid;
    }

    public ProcessNode getPreviousMode() {
        return previousMode;
    }

    public void setPreviousMode(ProcessNode previousMode) {
        this.previousMode = previousMode;
    }

    public ProcessNode getNextMode() {
        return nextMode;
    }

    public void setNextMode(ProcessNode nextMode) {
        this.nextMode = nextMode;
    }
}
