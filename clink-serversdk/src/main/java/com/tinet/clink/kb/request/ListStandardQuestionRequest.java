package com.tinet.clink.kb.request;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.ListStandardQuestionResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 获取标准问列表请求
 *
 * @author feizq
 * @date 2022/07/27
 **/
public class ListStandardQuestionRequest extends AbstractRequestModel<ListStandardQuestionResponse> {

    /**
     * 机器人ID
     */
    private String botId;

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
        if (botId != null) {
            putQueryParameter("botId", botId);
        }
    }

    public ListStandardQuestionRequest() {
        super(PathEnum.ListStandardQuestion.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListStandardQuestionResponse> getResponseClass() {
        return ListStandardQuestionResponse.class;
    }
}
