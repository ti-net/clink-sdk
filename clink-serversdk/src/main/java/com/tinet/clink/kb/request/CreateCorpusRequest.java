package com.tinet.clink.kb.request;


import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.model.CorpusRecordModel;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.CreateCorpusResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.List;

/**
 * 创建语料请求
 *
 * @author feizq
 * @date 2022/06/15
 **/
public class CreateCorpusRequest extends AbstractRequestModel<CreateCorpusResponse> {

    /**
     * 机器人ID
     */
    private String botId;
    /**
     * 语料记录集合
     */
    private List<CorpusRecordModel> records;

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
        if (botId != null) {
            putBodyParameter("botId", botId);
        }
    }

    public List<CorpusRecordModel> getRecords() {
        return records;
    }

    public void setRecords(List<CorpusRecordModel> records) {
        this.records = records;
        if (records != null) {
            putBodyParameter("records", records);
        }
    }

    public CreateCorpusRequest() {
        super(PathEnum.CreateCorpus.value(), HttpMethodType.POST);
    }

    @Override
    public Class<CreateCorpusResponse> getResponseClass() {
        return CreateCorpusResponse.class;
    }
}
