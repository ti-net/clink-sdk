package com.tinet.clink.kb.request;

import com.tinet.clink.kb.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.kb.response.DeleteStandardQuestionResponse;

/**
 * 删除标准问请求
 *
 * @author feizq
 * @date 2022/06/15
 **/
public class DeleteStandardQuestionRequest extends AbstractRequestModel<DeleteStandardQuestionResponse> {

    /**
     * 机器人ID
     */
    private String botId;
    /**
     * 标准问id数组
     */
    private Integer[] ids;

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
        if (botId != null) {
            putBodyParameter("botId", botId);
        }
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
        if (ids != null) {
            putBodyParameter("ids", ids);
        }
    }

    public DeleteStandardQuestionRequest() {
        super(PathEnum.DeleteStandardQuestion.value(), HttpMethodType.POST);
    }

    @Override
    public Class<DeleteStandardQuestionResponse> getResponseClass() {
        return DeleteStandardQuestionResponse.class;
    }
}
