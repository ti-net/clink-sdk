package com.tinet.clink.aikb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

/**
 * @author zhangpc
 * @since 2024/05/29
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatDescribeConversationResponseModel {

    private String content;

    /**
     * 对话创建时间
     */
    private Date createTime;

    /**
     * 消息角色：BOT，机器人
     * USER，用户
     */
    private String role;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
