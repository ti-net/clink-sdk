package com.tinet.clink.aikb.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tinet.clink.aikb.PathEnum;
import com.tinet.clink.aikb.model.FaqAnswerModel;
import com.tinet.clink.aikb.response.FaqCreateResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

import java.util.Date;
import java.util.List;

/**
 * @author houwd
 * @since 2024/06/25
 */
public class FaqCreateRequest extends AbstractRequestModel<FaqCreateResponse> {

    /**
     * 知识库id
     */
    private Integer repositoryId;

    /**
     * 目录
     */
    private Integer directoryId;

    /**
     * 知识简介
     */
    private String introduction;

    /**
     * 简介类型 1：普通文本，2：富文本
     */
    private Integer introductionType;

    /**
     * 标准问
     */
    private String question;

    /**
     * 答案
     */
    private List<FaqAnswerModel> answers;

    /**
     * 相似问
     */
    private List<String> similars;

    /**
     * 相关问知识id数组
     */
    private Integer[] relatedIds;

    /**
     * 知识有效开始时间；2024-01-26 10:00:00
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date onlineDateFrom;

    /**
     * 知识有效结束时间；2024-01-26 10:00:00
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date onlineDateTo;

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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getIntroductionType() {
        return introductionType;
    }

    public void setIntroductionType(Integer introductionType) {
        this.introductionType = introductionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<FaqAnswerModel> getAnswers() {
        return answers;
    }

    public void setAnswers(List<FaqAnswerModel> answers) {
        this.answers = answers;
    }

    public List<String> getSimilars() {
        return similars;
    }

    public void setSimilars(List<String> similars) {
        this.similars = similars;
    }

    public Integer[] getRelatedIds() {
        return relatedIds;
    }

    public void setRelatedIds(Integer[] relatedIds) {
        this.relatedIds = relatedIds;
    }

    public Date getOnlineDateFrom() {
        return onlineDateFrom;
    }

    public void setOnlineDateFrom(Date onlineDateFrom) {
        this.onlineDateFrom = onlineDateFrom;
    }

    public Date getOnlineDateTo() {
        return onlineDateTo;
    }

    public void setOnlineDateTo(Date onlineDateTo) {
        this.onlineDateTo = onlineDateTo;
    }

    public FaqCreateRequest() {
        super(PathEnum.CreateFaq.value(), HttpMethodType.POST);
    }

    @Override
    public Class<FaqCreateResponse> getResponseClass() {
        return FaqCreateResponse.class;
    }
}
