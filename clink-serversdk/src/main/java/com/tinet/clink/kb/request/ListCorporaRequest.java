package com.tinet.clink.kb.request;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.ListCorporaResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 获取语料列表请求
 *
 * @author feizq
 * @date 2022/07/26
 **/
public class ListCorporaRequest extends AbstractRequestModel<ListCorporaResponse> {

    /**
     * 机器人ID
     */
    private String botId;

    /**
     * 标准问ID
     */
    private Integer sqId;

    /**
     * 关键词
     */
    private String keyword;

    private Integer offset;

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

    public Integer getSqId() {
        return sqId;
    }

    public void setSqId(Integer sqId) {
        this.sqId = sqId;
        if (sqId != null) {
            putQueryParameter("sqId", sqId);
        }
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
        if (keyword != null) {
            putQueryParameter("keyword", keyword);
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

    public ListCorporaRequest() {
        super(PathEnum.ListCorpus.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListCorporaResponse> getResponseClass() {
        return ListCorporaResponse.class;
    }
}
