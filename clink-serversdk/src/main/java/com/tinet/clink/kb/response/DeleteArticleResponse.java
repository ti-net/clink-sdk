package com.tinet.clink.kb.response;

import com.tinet.clink.kb.model.KbArticleResponseModel;
import com.tinet.clink.core.response.ResponseModel;

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
