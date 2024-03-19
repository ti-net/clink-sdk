package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Class For:
 * 智能助手 - 知识推荐
 *
 * @author Tinet-yinzk
 * @date 2024/3/19 15:16
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssistantKnownledge {
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private List<String> content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
