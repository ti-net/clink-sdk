package com.tinet.clink.cc.request.assistant;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.model.AssistantTag;

import java.util.List;

/**
 * 标签推荐接口
 *
 * @author Tinet-yinzk
 * @date 2024/03/19
 **/
public class PushAssistantTagRequest extends com.tinet.clink.core.request.AbstractRequestModel<com.tinet.clink.cc.response.assistant.PushAssistantDataResponse> {

    /**
     * 通话唯一标识
     */
    private String mainUniqueId;
    /**
     * 座席工号
     */
    private String cno;
    /**
     * 转写文本角色；0：座席，1：客户
     */
    private Integer sentenceRole;
    /**
     * 转写序号
     */
    private Integer sentenceIndex;
    /**
     * 转写文本
     */
    private String sentenceText;
    /**
     * 转写文本开始时间戳，（秒级）
     */
    private Long sentenceTime;

    /**
     * 质检结果
     */
    private List<AssistantTag> tags;


    public PushAssistantTagRequest() {
        super(PathEnum.PushAssistantTag.value(), com.tinet.clink.core.utils.HttpMethodType.POST);
    }

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
        if (java.util.Objects.nonNull(mainUniqueId)) {
            putBodyParameter("mainUniqueId", mainUniqueId);
        }
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (java.util.Objects.nonNull(cno)) {
            putQueryParameter("cno", cno);
        }
    }

    public Long getSentenceTime() {
        return sentenceTime;
    }

    public void setSentenceTime(Long sentenceTime) {
        this.sentenceTime = sentenceTime;
        if (java.util.Objects.nonNull(sentenceTime)) {
            putQueryParameter("sentenceTime", sentenceTime);
        }
    }

    public String getSentenceText() {
        return sentenceText;
    }

    public void setSentenceText(String sentenceText) {
        this.sentenceText = sentenceText;
        if (java.util.Objects.nonNull(sentenceText)) {
            putQueryParameter("sentenceText", sentenceText);
        }
    }

    public Integer getSentenceIndex() {
        return sentenceIndex;
    }

    public void setSentenceIndex(Integer sentenceIndex) {
        this.sentenceIndex = sentenceIndex;
        if (java.util.Objects.nonNull(sentenceIndex)) {
            putQueryParameter("sentenceIndex", sentenceIndex);
        }
    }

    public Integer getSentenceRole() {
        return sentenceRole;
    }

    public void setSentenceRole(Integer sentenceRole) {
        this.sentenceRole = sentenceRole;
        if (java.util.Objects.nonNull(sentenceRole)) {
            putQueryParameter("sentenceRole", sentenceRole);
        }
    }

    public List<AssistantTag> getTags() {
        return tags;
    }

    public void setTags(List<AssistantTag> tags) {
        this.tags = tags;
    }

    @Override
    public Class<com.tinet.clink.cc.response.assistant.PushAssistantDataResponse> getResponseClass() {
        return com.tinet.clink.cc.response.assistant.PushAssistantDataResponse.class;
    }
}

