package com.tinet.clink.kb.response;

import com.tinet.clink.openapi.model.KbArticleModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 知识库文章列表响应
 *
 * @author feizq
 * @date 2021/06/25
 **/
public class ListArticlesResponse extends PagedResponse {

    List<KbArticleModel> articles;

    public List<KbArticleModel> getArticles() {
        return articles;
    }

    public void setArticles(List<KbArticleModel> articles) {
        this.articles = articles;
    }
}
