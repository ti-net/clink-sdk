package com.tinet.clink.kb.request;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.DescribeArticleResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 文章详情请求
 *
 * @author feizq
 * @date 2021/06/25
 **/
public class DescribeArticleRequest extends AbstractRequestModel<DescribeArticleResponse> {

    /**
     * 企业ID
     */
    private Integer enterpriseId;

    /**
     * 文章ID
     */
    private Integer id;

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
        if (enterpriseId != null) {
            putQueryParameter("enterpriseId", enterpriseId);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if (id != null) {
            putQueryParameter("id", id);
        }
    }

    public DescribeArticleRequest() {
        super(PathEnum.DescribeArticle.value(), HttpMethodType.GET);
    }

    @Override
    public Class<DescribeArticleResponse> getResponseClass() {
        return DescribeArticleResponse.class;
    }
}