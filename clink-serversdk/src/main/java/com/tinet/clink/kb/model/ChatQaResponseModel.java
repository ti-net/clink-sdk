package com.tinet.clink.kb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

/**
 * @author zhangpc
 * @since 2024/05/29
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatQaResponseModel {

    /**
     * 消息内容
     */
    private String content;

    /**
     * 会话ID
     */
    private String conversationId;

    /**
     * 消息内容类型：
     * 默认值：TEXT（文本）
     * IMAGE（图片，暂时不支持，后续扩展使用）
     */
    private String contentType;

    /**
     * 对话创建时间
     */
    private Date createTime;

    /**
     * 文件来源集合
     */
    private List<FileKnowledgeSource> files;

    /**
     * 问答知识来源集合
     */
    private List<FaqKnowledgeSource> faqs;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<FileKnowledgeSource> getFiles() {
        return files;
    }

    public void setFiles(List<FileKnowledgeSource> files) {
        this.files = files;
    }

    public List<FaqKnowledgeSource> getFaqs() {
        return faqs;
    }

    public void setFaqs(List<FaqKnowledgeSource> faqs) {
        this.faqs = faqs;
    }

    public static class FileKnowledgeSource {
        /**
         * 知识来源id
         */
        private Integer id;

        /**
         * 知识来源名称
         */
        private String name;

        /**
         * oss fileKey
         */
        private String fileKey;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFileKey() {
            return fileKey;
        }

        public void setFileKey(String fileKey) {
            this.fileKey = fileKey;
        }
    }

    public static class FaqKnowledgeSource {
        /**
         * 知识来源id
         */
        private Integer id;

        /**
         * 知识来源名称
         */
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
