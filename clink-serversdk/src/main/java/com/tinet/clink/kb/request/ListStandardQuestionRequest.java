package com.tinet.clink.kb.request;

import com.tinet.clink.kb.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.kb.response.ListStandardQuestionResponse;

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
