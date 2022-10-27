package com.tinet.clink.openapi.request.kb;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.CreateCategoryResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 创建分类请求
 *
 * @author feizq
 * @date 2022/06/20
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  CreateCategoryRequest extends AbstractRequestModel<CreateCategoryResponse> {

    /**
     * 机器人ID
     */
    private String botId;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 父类ID，一级分类，父类ID为0
     */
    private Integer parentId;

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
        if (parentId != null) {
            putBodyParameter("parentId", parentId);
        }
    }

    public CreateCategoryRequest() {
        super(PathEnum.CreateCategory.value(), HttpMethodType.POST);
    }

    @Override
    public Class<CreateCategoryResponse> getResponseClass() {
        return CreateCategoryResponse.class;
    }
}
