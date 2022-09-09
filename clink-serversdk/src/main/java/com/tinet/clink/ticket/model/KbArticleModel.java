package com.tinet.clink.ticket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

/**
 * 文章返回对象
 *
 * @author feizq
 * @date 2021/06/25
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class KbArticleModel {

    private String id;

    /**
     * 企业ID
     */
    private String enterpriseId;

    /**
     * 标题
     */
    private String title;

    /**
     * 相似文章
     */
    private String similars;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 电话回复内容
     */
    private String phoneContent;
    /**
     * 在线回复文章内容
     */
    private String onlineContent;
    /**
     * 微信回复文章内容
     */
    private String wechatContent;
    /**
     * app回复文章内容
     */
    private String appContent;
    /**
     * 网页回复文章内容
     */
    private String webContent;

    /**
     * 目录ID
     */
    private Integer directoryId;

    /**
     * 知识库ID
     */
    private Integer kbId;

    /**
     * 有效起始日期
     */
    private Date validDateFrom;

    /**
     * 有效终止时间
     */
    private Date validDateTo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 平台登录名
     */
    private String accountLoginName;
    /**
     * 目录路径
     */
    private List<String> path;

    /**
     * 文章点赞数量
     */
    private Integer likeCount;

    /**
     * 文章点踩数量
     */
    private Integer dislikeCount;

    /**
     * 0 已点赞 1 已点踩  null 未被点击
     */
    private String status;

    /**
     * 问答库 多个答案
     */
    private List<Answer> answers;

    /**
     * 机器人ID
     */
    private String botId;
    /**
     * 机器人配置-标签
     */
    private String[] botTag;

    /**
     * 机器人配置-标签
     */
    private String[] botAction;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Answer {
        private String answer;

        private String[] recommendQuestion;

        private String startTime;
        private String endTime;

        /**
         * 转人工 0不选中 1选中
         */
        private Integer transfer;

        /**
         * 问答库-关联知识点
         */
        private List<RelatedQuestion> relatedQuestions;

        public List<RelatedQuestion> getRelatedQuestions() {
            return relatedQuestions;
        }

        public void setRelatedQuestions(List<RelatedQuestion> relatedQuestions) {
            this.relatedQuestions = relatedQuestions;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String[] getRecommendQuestion() {
            return recommendQuestion;
        }

        public void setRecommendQuestion(String[] recommendQuestion) {
            this.recommendQuestion = recommendQuestion;
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

        public Integer getTransfer() {
            return transfer;
        }

        public void setTransfer(Integer transfer) {
            this.transfer = transfer;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RelatedQuestion {

        /**
         * 知识点标题
         */
        private String relatedQuestion;

        /**
         * 标题ID
         */
        private String relatedQuestionId;

        public String getRelatedQuestion() {
            return relatedQuestion;
        }

        public void setRelatedQuestion(String relatedQuestion) {
            this.relatedQuestion = relatedQuestion;
        }

        public String getRelatedQuestionId() {
            return relatedQuestionId;
        }

        public void setRelatedQuestionId(String relatedQuestionId) {
            this.relatedQuestionId = relatedQuestionId;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSimilars() {
        return similars;
    }

    public void setSimilars(String similars) {
        this.similars = similars;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoneContent() {
        return phoneContent;
    }

    public void setPhoneContent(String phoneContent) {
        this.phoneContent = phoneContent;
    }

    public String getOnlineContent() {
        return onlineContent;
    }

    public void setOnlineContent(String onlineContent) {
        this.onlineContent = onlineContent;
    }

    public String getWechatContent() {
        return wechatContent;
    }

    public void setWechatContent(String wechatContent) {
        this.wechatContent = wechatContent;
    }

    public String getAppContent() {
        return appContent;
    }

    public void setAppContent(String appContent) {
        this.appContent = appContent;
    }

    public String getWebContent() {
        return webContent;
    }

    public void setWebContent(String webContent) {
        this.webContent = webContent;
    }

    public Integer getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(Integer directoryId) {
        this.directoryId = directoryId;
    }

    public Integer getKbId() {
        return kbId;
    }

    public void setKbId(Integer kbId) {
        this.kbId = kbId;
    }

    public Date getValidDateFrom() {
        return validDateFrom;
    }

    public void setValidDateFrom(Date validDateFrom) {
        this.validDateFrom = validDateFrom;
    }

    public Date getValidDateTo() {
        return validDateTo;
    }

    public void setValidDateTo(Date validDateTo) {
        this.validDateTo = validDateTo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getAccountLoginName() {
        return accountLoginName;
    }

    public void setAccountLoginName(String accountLoginName) {
        this.accountLoginName = accountLoginName;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
    }

    public String[] getBotTag() {
        return botTag;
    }

    public void setBotTag(String[] botTag) {
        this.botTag = botTag;
    }

    public String[] getBotAction() {
        return botAction;
    }

    public void setBotAction(String[] botAction) {
        this.botAction = botAction;
    }
}
