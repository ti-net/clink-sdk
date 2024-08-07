package com.tinet.clink.openapi.request.kb;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.DeleteAnswerResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 删除答案请求
 *
 * @author feizq
 * @date 2022/06/15
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DeleteAnswerRequest extends AbstractRequestModel<DeleteAnswerResponse> {

    /**
     * 机器人ID
     */
    private String botId;
    /**
     * 答案id数组
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

    public DeleteAnswerRequest() {
        super(PathEnum.DeleteAnswer.value(), HttpMethodType.POST);
    }

    @Override
    public Class<DeleteAnswerResponse> getResponseClass() {
        return DeleteAnswerResponse.class;
    }
}
