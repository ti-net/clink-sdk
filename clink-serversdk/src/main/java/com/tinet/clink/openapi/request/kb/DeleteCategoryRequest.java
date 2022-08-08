package com.tinet.clink.openapi.request.kb;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.DeleteCategoryResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 删除分类请求
 *
 * @author feizq
 * @date 2022/06/20
 **/
public class DeleteCategoryRequest extends AbstractRequestModel<DeleteCategoryResponse> {

    /**
     * 机器人ID
     */
    private String botId;
    /**
     * 分类ID
     */
    private Integer id;

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
        if (botId != null) {
            putBodyParameter("botId", botId);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if (id != null) {
            putBodyParameter("id", id);
        }
    }

    public DeleteCategoryRequest() {
        super(PathEnum.DeleteCategory.value(), HttpMethodType.POST);
    }

    @Override
    public Class<DeleteCategoryResponse> getResponseClass() {
        return DeleteCategoryResponse.class;
    }
}
