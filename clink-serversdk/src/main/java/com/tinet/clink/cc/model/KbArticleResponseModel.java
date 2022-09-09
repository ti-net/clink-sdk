package com.tinet.clink.cc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 文章响应实体
 *
 * @author feizq
 * @date 2021/11/26
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class KbArticleResponseModel {

    /**
     * 文章id
     */
    private Integer id;

    /**
     * 知识库ID
     */
    private Integer kbId;

    /**
     * 知识库类型
     */
    private Integer kbType;

    /**
     * 目录ID
     */
    private Integer directoryId;

    /**
     * 标题
     */
    private String title;

    /**
     * 座席可用状态
     */
    private Integer agentEnabled;

    /**
     * 相似问题
     */
    private String[] similars;

    /**
     * 文档库 答案
     */
    private String content;

    /**
     * 置顶项
     */
    private Integer topStatus;

    /**
     * 机器人ID
     */
    private String botId;

    /**
     * 关联标题
     */
    private List<KbArticleModel.RelatedQuestion> relatedQuestions;

    /**
     * 问答库 答案
     */
    private List<KbArticleModel.Answer> answers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKbId() {
        return kbId;
    }

    public void setKbId(Integer kbId) {
        this.kbId = kbId;
    }

    public Integer getKbType() {
        return kbType;
    }

    public void setKbType(Integer kbType) {
        this.kbType = kbType;
    }

    public Integer getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(Integer directoryId) {
        this.directoryId = directoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAgentEnabled() {
        return agentEnabled;
    }

    public void setAgentEnabled(Integer agentEnabled) {
        this.agentEnabled = agentEnabled;
    }

    public String[] getSimilars() {
        return similars;
    }

    public void setSimilars(String[] similars) {
        this.similars = similars;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTopStatus() {
        return topStatus;
    }

    public void setTopStatus(Integer topStatus) {
        this.topStatus = topStatus;
    }

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
    }

    public List<KbArticleModel.RelatedQuestion> getRelatedQuestions() {
        return relatedQuestions;
    }

    public void setRelatedQuestions(List<KbArticleModel.RelatedQuestion> relatedQuestions) {
        this.relatedQuestions = relatedQuestions;
    }

    public List<KbArticleModel.Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<KbArticleModel.Answer> answers) {
        this.answers = answers;
    }
}
