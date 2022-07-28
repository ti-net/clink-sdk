package com.tinet.clink.openapi.request.kb;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.UpdateCategoryResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 更新分类请求
 *
 * @author feizq
 * @date 2022/06/20
 **/
public class UpdateCategoryRequest extends AbstractRequestModel<UpdateCategoryResponse> {

    /**
     * 机器人ID
     */
    private String botId;
    /**
     * 分类名称
     */
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (name != null) {
            putBodyParameter("name", name);
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

    public UpdateCategoryRequest() {
        super(PathEnum.UpdateCategory.value(), HttpMethodType.POST);
    }

    @Override
    public Class<UpdateCategoryResponse> getResponseClass() {
        return UpdateCategoryResponse.class;
    }
}
