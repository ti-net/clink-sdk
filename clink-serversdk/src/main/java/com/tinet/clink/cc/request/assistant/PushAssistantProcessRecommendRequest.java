package com.tinet.clink.cc.request.assistant;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.model.ProcessNode;
import com.tinet.clink.cc.response.assistant.PushAssistantDataResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

import java.util.List;
import java.util.Objects;

/**
 * 流程话术推荐接口
 *
 * @author Tinet-yinzk
 * @date 2024/03/19
 **/
public class PushAssistantProcessRecommendRequest extends AbstractRequestModel<PushAssistantDataResponse> {

    /**
     * 通话唯一标识
     */
    private String mainUniqueId;
    /**
     * 座席工号
     */
    private String cno;
    /**
     * 转写文本开始时间戳，（秒级）
     */
    private Long sentenceTime;
    /**
     * 转写文本
     */
    private String sentenceText;
    /**
     * 转写序号
     */
    private Integer sentenceIndex;
    /**
     * 流程话术节点
     */
    private List<ProcessNode> processNodes;


    public PushAssistantProcessRecommendRequest() {
        super(PathEnum.PushAssistantProcessRecommend.value(), HttpMethodType.POST);
    }

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
        if (Objects.nonNull(mainUniqueId)) {
            putBodyParameter("mainUniqueId", mainUniqueId);
        }
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (Objects.nonNull(cno)) {
            putQueryParameter("cno", cno);
        }
    }

    public Long getSentenceTime() {
        return sentenceTime;
    }

    public void setSentenceTime(Long sentenceTime) {
        this.sentenceTime = sentenceTime;
        if (Objects.nonNull(sentenceTime)) {
            putQueryParameter("sentenceTime", sentenceTime);
        }
    }

    public String getSentenceText() {
        return sentenceText;
    }

    public void setSentenceText(String sentenceText) {
        this.sentenceText = sentenceText;
        if (Objects.nonNull(sentenceText)) {
            putQueryParameter("sentenceText", sentenceText);
        }
    }

    public Integer getSentenceIndex() {
        return sentenceIndex;
    }

    public void setSentenceIndex(Integer sentenceIndex) {
        this.sentenceIndex = sentenceIndex;
        if (Objects.nonNull(sentenceIndex)) {
            putQueryParameter("sentenceIndex", sentenceIndex);
        }
    }

    public List<ProcessNode> getProcessNodes() {
        return processNodes;
    }

    public void setProcessNodes(List<ProcessNode> processNodes) {
        this.processNodes = processNodes;
        if (Objects.nonNull(processNodes)) {
            putBodyParameter("processNodes", processNodes);
        }
    }

    @Override
    public Class<PushAssistantDataResponse> getResponseClass() {
        return PushAssistantDataResponse.class;
    }
}

