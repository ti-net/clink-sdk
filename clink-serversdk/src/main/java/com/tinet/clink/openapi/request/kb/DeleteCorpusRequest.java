package com.tinet.clink.openapi.request.kb;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.DeleteCorpusResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.List;

/**
 * 删除语料请求
 *
 * @author feizq
 * @date 2022/06/15
 **/
public class DeleteCorpusRequest extends AbstractRequestModel<DeleteCorpusResponse> {

    /**
     * 机器人ID
     */
    private String botId;
    /**
     * 标准问ID
     */
    private Integer sqId;
    /**
     * 语料名称
     */
    private List<String> corpusNames;

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

    public List<String> getCorpusNames() {
        return corpusNames;
    }

    public void setCorpusNames(List<String> corpusNames) {
        this.corpusNames = corpusNames;
        if (corpusNames != null) {
            putBodyParameter("corpusNames", corpusNames);
        }
    }

    public DeleteCorpusRequest() {
        super(PathEnum.DeleteCorpus.value(), HttpMethodType.POST);
    }

    @Override
    public Class<DeleteCorpusResponse> getResponseClass() {
        return DeleteCorpusResponse.class;
    }
}
