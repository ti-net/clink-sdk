package com.tinet.clink.openapi.request.kb;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.PageStandardQuestionResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 获取标准问分页请求
 *
 * @author feizq
 * @date 2022/09/22
 **/
public class PageStandardQuestionRequest extends AbstractRequestModel<PageStandardQuestionResponse> {

    /**
     * 机器人ID
     */
    private String botId;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 偏移量
     */
    private Integer offset;

    /**
     * 查询记录条数
     */
    private Integer limit;

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
        if (botId != null) {
            putQueryParameter("botId", botId);
        }
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
        if (updateTime != null) {
            putQueryParameter("updateTime", updateTime);
        }
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (offset != null) {
            putQueryParameter("offset", offset);
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (limit != null) {
            putQueryParameter("limit", limit);
        }
    }

    public PageStandardQuestionRequest() {
        super(PathEnum.PageStandardQuestion.value(), HttpMethodType.GET);
    }

    @Override
    public Class<PageStandardQuestionResponse> getResponseClass() {
        return PageStandardQuestionResponse.class;
    }
}
