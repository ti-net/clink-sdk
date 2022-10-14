package com.tinet.clink.openapi.request.kb;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.UpdateCorpusResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 更新语料请求
 *
 * @author feizq
 * @date 2022/06/15
 **/
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  UpdateCorpusRequest extends AbstractRequestModel<UpdateCorpusResponse> {

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
