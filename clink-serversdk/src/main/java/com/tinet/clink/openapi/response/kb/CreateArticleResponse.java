package com.tinet.clink.openapi.response.kb;

import com.tinet.clink.openapi.model.KbArticleResponseModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 创建文章响应实体
 *
 * @author feizq
 * @date 2021/11/29
 **/
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  CreateArticleResponse extends ResponseModel {

    private KbArticleResponseModel article;

    public KbArticleResponseModel getArticle() {
        return article;
    }

    public void setArticle(KbArticleResponseModel article) {
        this.article = article;
    }
}
