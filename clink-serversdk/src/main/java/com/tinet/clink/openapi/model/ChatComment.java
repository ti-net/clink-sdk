package com.tinet.clink.openapi.model;

/**
 * 会话留言记录索引类
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ChatComment {

    /**
     * 主会话唯一标识
     */
    private String mainUniqueId;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Long createTime;


    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}

