package com.tinet.clink.openapi.response.kb;

import com.tinet.clink.openapi.model.KbArticleResponseModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 删除文章响应实体
 *
 * @author feizq
 * @date 2021/11/29
 **/
public class DeleteArticleResponse extends ResponseModel {

    private KbArticleResponseModel article;

    public KbArticleResponseModel getArticle() {
        return article;
    }

    public void setArticle(KbArticleResponseModel article) {
        this.article = article;
    }
}
