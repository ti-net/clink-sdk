package com.tinet.clink.aikb.model;

/**
 * @author zhangxy
 * @since 2024/06/06
 */
public class FaqAnswerModel {

    /**
     * 答案内容
     */
    private String content;

    /**
     * 答案类型
     * 答案类型 text：普通文本，html：富文本
     */
    private String type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
