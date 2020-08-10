package com.tinet.clink.openapi.model.chat;

/**
 * 文本消息内容
 *
 * @author wangzb
 * @date 2020/8/8
 */
public class TextPayload extends MessagePayload {

    /**
     * 文本消息内容
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
