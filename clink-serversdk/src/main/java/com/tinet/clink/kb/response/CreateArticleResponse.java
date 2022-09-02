package com.tinet.clink.kb.response;

import com.tinet.clink.openapi.model.KbArticleResponseModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 创建文章响应实体
 *
 * @author feizq
 * @date 2021/11/29
 **/
public class CreateArticleResponse extends ResponseModel {

    private KbArticleResponseModel article;

    public KbArticleResponseModel getArticle() {
        return article;
    }

    public void setArticle(KbArticleResponseModel article) {
        this.article = article;
    }
}
