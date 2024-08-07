package com.tinet.clink.kb.request;

import com.tinet.clink.kb.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.kb.response.DeleteCorpusResponse;

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
