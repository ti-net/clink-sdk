package com.tinet.clink.kb.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.kb.PathEnum;
import com.tinet.clink.kb.model.SearchKnowledgeOnOpenModel;
import com.tinet.clink.kb.response.ChatConversationResponse;
import com.tinet.clink.kb.response.SearchKnowledgeOnOpenResponse;

import java.util.Set;

/**
 * @author zhangpc
 * @since 2024/06/05
 */
public class SearchKnowledgeOnOpenRequest extends AbstractRequestModel<SearchKnowledgeOnOpenResponse> {

    /**
     * 用户 Id
     */
    private Integer userId;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 排序字段；createTime：创建时间，searchCount：热度，likeCount：点赞，updateTime，修改时间，不传按照默认排序规则
     */
    private String orderField;
    /**
     * 知识类型
     */
    private SearchKnowledgeOnOpenModel.KnowledgeType knowledgeType;
    /**
     * 知识库IDs
     */
    private Set<Integer> repositoryIds;
    /**
     * 目录IDs
     */
    private Set<Integer> directoryIds;
    /**
     * 是否拼音检索
     */
    private Boolean pinyinQuery = false;
    /**
     * 偏移量，页起始位置
     */
    private Integer offset = 0;
    /**
     * 一页数据条数
     */
    private Integer limit = 10;

    public Integer getUserId() {
        return userId;

    }

    public void setUserId(Integer userId) {
        this.userId = userId;
        if (userId != null) {
            putBodyParameter("userId", userId);
        }
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
        if (keyword != null) {
            putBodyParameter("keyword", keyword);
        }
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
        if (keyword != null) {
            putBodyParameter("orderField", orderField);
        }
    }

    public SearchKnowledgeOnOpenModel.KnowledgeType getKnowledgeType() {
        return knowledgeType;
    }

    public void setKnowledgeType(SearchKnowledgeOnOpenModel.KnowledgeType knowledgeType) {
        this.knowledgeType = knowledgeType;
        if (knowledgeType != null) {
            putBodyParameter("knowledgeType", knowledgeType);
        }
    }

    public Set<Integer> getRepositoryIds() {
        return repositoryIds;
    }

    public void setRepositoryIds(Set<Integer> repositoryIds) {
        this.repositoryIds = repositoryIds;
        if (repositoryIds != null) {
            putBodyParameter("repositoryIds", repositoryIds);
        }
    }

    public Set<Integer> getDirectoryIds() {
        return directoryIds;
    }

    public void setDirectoryIds(Set<Integer> directoryIds) {
        this.directoryIds = directoryIds;
        if (repositoryIds != null) {
            putBodyParameter("directoryIds", directoryIds);
        }
    }

    public Boolean getPinyinQuery() {
        return pinyinQuery;
    }

    public void setPinyinQuery(Boolean pinyinQuery) {
        this.pinyinQuery = pinyinQuery;
        if (pinyinQuery != null) {
            putBodyParameter("pinyinQuery", pinyinQuery);
        }
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (offset != null) {
            putBodyParameter("offset", offset);
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (limit != null) {
            putBodyParameter("limit", limit);
        }
    }

    public SearchKnowledgeOnOpenRequest() {
        super(PathEnum.SearchKnowledgeOnOpen.value(), HttpMethodType.POST);
    }

    @Override
    public Class<SearchKnowledgeOnOpenResponse> getResponseClass() {
        return SearchKnowledgeOnOpenResponse.class;
    }
}
