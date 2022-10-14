package com.tinet.clink.kb.request;


import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.kb.PathEnum;
import com.tinet.clink.kb.response.DeleteCategoryResponse;

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
