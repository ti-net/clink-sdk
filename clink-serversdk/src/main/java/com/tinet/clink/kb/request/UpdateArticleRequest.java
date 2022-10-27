package com.tinet.clink.kb.request;

import com.tinet.clink.kb.PathEnum;
import com.tinet.clink.kb.model.KbArticleModel;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.kb.response.UpdateArticleResponse;

import java.util.List;

/**
 * 更新文章请求
 *
 * @author feizq
 * @date 2021/11/29
 **/
public class UpdateArticleRequest extends AbstractRequestModel<UpdateArticleResponse> {

    /**
     * 文章ID
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
        if (id != null) {
            putBodyParameter("id", id);
        }
    }

    public Integer getKbId() {
        return kbId;
    }

    public void setKbId(Integer kbId) {
        this.kbId = kbId;
        if (kbId != null) {
            putBodyParameter("kbId", kbId);
        }
    }

    public Integer getKbType() {
        return kbType;
    }

    public void setKbType(Integer kbType) {
        this.kbType = kbType;
        if (kbType != null) {
            putBodyParameter("kbType", kbType);
        }
    }

    public Integer getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(Integer directoryId) {
        this.directoryId = directoryId;
        if (directoryId != null) {
            putBodyParameter("directoryId", directoryId);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        if (title != null) {
            putBodyParameter("title", title);
        }
    }

    public Integer getAgentEnabled() {
        return agentEnabled;
    }

    public void setAgentEnabled(Integer agentEnabled) {
        this.agentEnabled = agentEnabled;
        if (agentEnabled != null) {
            putBodyParameter("agentEnabled", agentEnabled);
        }
    }

    public String[] getSimilars() {
        return similars;
    }

    public void setSimilars(String[] similars) {
        this.similars = similars;
        if (similars != null) {
            putBodyParameter("similars", similars);
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        if (content != null) {
            putBodyParameter("content", content);
        }
    }

    public Integer getTopStatus() {
        return topStatus;
    }

    public void setTopStatus(Integer topStatus) {
        this.topStatus = topStatus;
        if (topStatus != null) {
            putBodyParameter("topStatus", topStatus);
        }
    }

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
        if (botId != null) {
            putBodyParameter("botId", botId);
        }
    }

    public List<KbArticleModel.RelatedQuestion> getRelatedQuestions() {
        return relatedQuestions;
    }

    public void setRelatedQuestions(List<KbArticleModel.RelatedQuestion> relatedQuestions) {
        this.relatedQuestions = relatedQuestions;
        if (relatedQuestions != null) {
            putBodyParameter("relatedQuestions", relatedQuestions);
        }
    }

    public List<KbArticleModel.Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<KbArticleModel.Answer> answers) {
        this.answers = answers;
        if (answers != null) {
            putBodyParameter("answers", answers);
        }
    }

    public UpdateArticleRequest() {
        super(PathEnum.UpdateArticle.value(), HttpMethodType.POST);
    }

    @Override
    public Class<UpdateArticleResponse> getResponseClass() {
        return UpdateArticleResponse.class;
    }
}
