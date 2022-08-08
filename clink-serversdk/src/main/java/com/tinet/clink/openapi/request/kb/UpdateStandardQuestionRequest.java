package com.tinet.clink.openapi.request.kb;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.StandardQuestionResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 更新标准问请求
 *
 * @author feizq
 * @date 2022/06/15
 **/
public class UpdateStandardQuestionRequest extends AbstractRequestModel<StandardQuestionResponse> {

    /**
     * 标准问ID
     */
    private Integer id;
    /**
     * 机器人ID
     */
    private String botId;
    /**
     * 标准问问题
     */
    private String title;
    /**
     * 分类ID
     */
    private Integer categoryId;
    /**
     * 标签ID集合
     */
    private String[] tagIdList;
    /**
     * 状态
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if (id != null) {
            putBodyParameter("id", id);
        }
    }

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
        if (botId != null) {
            putBodyParameter("botId", botId);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        if (title != null) {
            putBodyParameter("title", title);
        }
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
        if (categoryId != null) {
            putBodyParameter("categoryId", categoryId);
        }
    }

    public String[] getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(String[] tagIdList) {
        this.tagIdList = tagIdList;
        if (tagIdList != null) {
            putBodyParameter("tagIdList", tagIdList);
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
        if (status != null) {
            putBodyParameter("status", status);
        }
    }

    public UpdateStandardQuestionRequest() {
        super(PathEnum.UpdateStandardQuestion.value(), HttpMethodType.POST);
    }

    @Override
    public Class<StandardQuestionResponse> getResponseClass() {
        return StandardQuestionResponse.class;
    }
}
