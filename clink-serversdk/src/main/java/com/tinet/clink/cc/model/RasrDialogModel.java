package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author Chenjf
 * @date 2020/9/28 15:16
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RasrDialogModel {
    /**
     * 角色
     */
    private String role;

    /**
     * 说话时间   毫秒级时间戳(历史问题)
     */
    private Long speakTime;

    /**
     * 内容
     */
    private String text;

    /**
     * 动作
     */
    private List<String> action;

    /**
     * 意图
     */
    private String intent;

    /**
     * 标签
     */
    private List<String> tag;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getSpeakTime() {
        return speakTime;
    }

    public void setSpeakTime(Long speakTime) {
        this.speakTime = speakTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getAction() {
        return action;
    }

    public void setAction(List<String> action) {
        this.action = action;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }
}
