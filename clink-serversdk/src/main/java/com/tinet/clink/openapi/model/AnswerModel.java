package com.tinet.clink.openapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 竹间答案实体
 *
 * @author feizq
 * @date 2022/07/26
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerModel {

    /**
     * 答案ID
     */
    private Integer id;
    /**
     * 标准问ID
     */
    private Integer sqId;
    /**
     * 答案
     */
    private String answer;
    /**
     * 有效开始时间
     */
    private String startTime;
    /**
     * 有效结束时间
     */
    private String endTime;
    /**
     * 类型；1：每天，0：无
     */
    private Integer periodType;

    /**
     * 相关问集合
     */
    private List<RelatedQuestion> relatedQuestionList;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RelatedQuestion {
        /**
         * 相关问ID
         */
        private Integer relatedQuestionId;
        /**
         * 相关问标题
         */
        private String relatedQuestionTitle;

        public Integer getRelatedQuestionId() {
            return relatedQuestionId;
        }

        public void setRelatedQuestionId(Integer relatedQuestionId) {
            this.relatedQuestionId = relatedQuestionId;
        }

        public String getRelatedQuestionTitle() {
            return relatedQuestionTitle;
        }

        public void setRelatedQuestionTitle(String relatedQuestionTitle) {
            this.relatedQuestionTitle = relatedQuestionTitle;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSqId() {
        return sqId;
    }

    public void setSqId(Integer sqId) {
        this.sqId = sqId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getPeriodType() {
        return periodType;
    }

    public void setPeriodType(Integer periodType) {
        this.periodType = periodType;
    }

    public List<RelatedQuestion> getRelatedQuestionList() {
        return relatedQuestionList;
    }

    public void setRelatedQuestionList(List<RelatedQuestion> relatedQuestionList) {
        this.relatedQuestionList = relatedQuestionList;
    }
}
