package com.tinet.clink.openapi.request.kb;


import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.ListAnswerResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author feizq
 * @date 2022/07/26
 **/
public class ListAnswerRequest extends AbstractRequestModel<ListAnswerResponse> {

    /**
     * 机器人ID
     */
    private String botId;

    /**
     * 标准问ID
     */
    private Integer sqId;

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
        if (botId != null) {
            putQueryParameter("botId", botId);
        }
    }

    public Integer getSqId() {
        return sqId;
    }

    public void setSqId(Integer sqId) {
        this.sqId = sqId;
        if (sqId != null) {
            putQueryParameter("sqId", sqId);
        }
    }

    public ListAnswerRequest() {
        super(PathEnum.ListAnswer.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListAnswerResponse> getResponseClass() {
        return ListAnswerResponse.class;
    }
}