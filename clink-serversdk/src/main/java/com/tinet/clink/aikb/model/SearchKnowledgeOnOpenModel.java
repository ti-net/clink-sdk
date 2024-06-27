package com.tinet.clink.aikb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @author zhangpc
 * @since 2024/06/05
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchKnowledgeOnOpenModel {

    /**
     * ID
     */
    private String id;

    /**
     * 知识标题
     */
    private String question;

    /**
     * 知识标题
     */
    private String name;

    /**
     * 知识库ID
     */
    private Integer repositoryId;

    /**
     * 目录ID
     */
    private Integer directoryId;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 点踩数
     */
    private Integer dislikeCount;

    /**
     * 点击状态；true：已点赞，false：已点踩，null：未被点击
     */
    private Boolean liked;

    /**
     * 搜索热度数
     */
    private Integer searchCount;

    /**
     * 答案
     */
    private List<AnswerVO> answers;

    /**
     * 文档库 - 答案
     */
    private String content;

    /**
     * 文档库 - 简介
     */
    private String introduction;

    /**
     * 目录名称（全路径）
     */
    private String directoryNames;

    /**
     * 文件key
     */
    private String objectKey;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 相似问集合
     */
    private List<String> similarList;

    /**
     * 知识类型
     */
    private KnowledgeType knowledgeType;
    /**
     * 附件
     */
    private List<Attachment> attachments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(Integer repositoryId) {
        this.repositoryId = repositoryId;
    }

    public Integer getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(Integer directoryId) {
        this.directoryId = directoryId;
    }


    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(Integer dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public Integer getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Integer searchCount) {
        this.searchCount = searchCount;
    }

    public List<AnswerVO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerVO> answers) {
        this.answers = answers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDirectoryNames() {
        return directoryNames;
    }

    public void setDirectoryNames(String directoryNames) {
        this.directoryNames = directoryNames;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getSimilarList() {
        return similarList;
    }

    public void setSimilarList(List<String> similarList) {
        this.similarList = similarList;
    }

    public KnowledgeType getKnowledgeType() {
        return knowledgeType;
    }

    public void setKnowledgeType(KnowledgeType knowledgeType) {
        this.knowledgeType = knowledgeType;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    private static class Attachment {

        /**
         * 文件key
         */
        private String fileKey;

        /**
         * 文件名
         */
        private String fileName;


        public String getFileKey() {
            return fileKey;
        }

        public void setFileKey(String fileKey) {
            this.fileKey = fileKey;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }

    public enum KnowledgeType {

        /**
         * 问答库
         */
        FAQ,

        /**
         * 文档库
         */
        FILE,
    }

    public static class AnswerVO {
        /**
         * FaqID
         */
        private Integer faqId;
        /**
         * 内容
         */
        private String content;
        /**
         * 答案类型
         */
        private String type;

        public Integer getFaqId() {
            return faqId;
        }

        public void setFaqId(Integer faqId) {
            this.faqId = faqId;
        }

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

}
