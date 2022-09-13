package com.tinet.clink.kb.request;

import com.tinet.clink.kb.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.kb.response.UpdateCorpusResponse;

/**
 * 更新语料请求
 *
 * @author feizq
 * @date 2022/06/15
 **/
public class UpdateCorpusRequest extends AbstractRequestModel<UpdateCorpusResponse> {

    /**
     * 机器人ID
     */
    private String botId;
    /**
     * 标准问ID
     */
    private Integer sqId;
    /**
     * 原始语料名称
     */
    private String sourceCorpusName;
    /**
     * 要修改语料名称
     */
    private String targetCorpusName;

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
        if (botId != null) {
            putBodyParameter("botId", botId);
        }
    }

    public Integer getSqId() {
        return sqId;
    }

    public void setSqId(Integer sqId) {
        this.sqId = sqId;
        if (sqId != null) {
            putBodyParameter("sqId", sqId);
        }
    }

    public String getSourceCorpusName() {
        return sourceCorpusName;
    }

    public void setSourceCorpusName(String sourceCorpusName) {
        this.sourceCorpusName = sourceCorpusName;
        if (sourceCorpusName != null) {
            putBodyParameter("sourceCorpusName", sourceCorpusName);
        }
    }

    public String getTargetCorpusName() {
        return targetCorpusName;
    }

    public void setTargetCorpusName(String targetCorpusName) {
        this.targetCorpusName = targetCorpusName;
        if (targetCorpusName != null) {
            putBodyParameter("targetCorpusName", targetCorpusName);
        }
    }

    public UpdateCorpusRequest() {
        super(PathEnum.UpdateCorpus.value(), HttpMethodType.POST);
    }

    @Override
    public Class<UpdateCorpusResponse> getResponseClass() {
        return UpdateCorpusResponse.class;
    }
}
