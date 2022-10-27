package com.tinet.clink.openapi.response.kb;

import com.tinet.clink.openapi.model.KbArticleModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 知识库文章列表响应
 *
 * @author feizq
 * @date 2021/06/25
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListArticlesResponse extends PagedResponse {

    List<KbArticleModel> articles;

    public List<KbArticleModel> getArticles() {
        return articles;
    }

    public void setArticles(List<KbArticleModel> articles) {
        this.articles = articles;
    }
}
