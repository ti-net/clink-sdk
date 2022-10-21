package com.tinet.clink.kb.response;

import com.tinet.clink.kb.model.KbArticleModel;
import com.tinet.clink.core.response.PagedResponse;

/**
 * 文章详情响应实体
 *
 * @author feizq
 * @date 2021/06/25
 **/
public class DescribeArticleResponse extends PagedResponse {

    private KbArticleModel article;

    public KbArticleModel getArticle() {
        return article;
    }

    public void setArticle(KbArticleModel article) {
        this.article = article;
    }
}
