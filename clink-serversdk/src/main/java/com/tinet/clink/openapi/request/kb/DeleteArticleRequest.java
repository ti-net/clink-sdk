package com.tinet.clink.openapi.request.kb;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.DeleteArticleResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 删除文章请求
 *
 * @author feizq
 * @date 2021/11/29
 **/
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  DeleteArticleRequest extends AbstractRequestModel<DeleteArticleResponse> {

    /**
     * 文章Id
     */
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if (id != null) {
            putQueryParameter("id", id);
        }
    }

    public DeleteArticleRequest() {
        super(PathEnum.DeleteArticle.value(), HttpMethodType.POST);
    }

    @Override
    public Class<DeleteArticleResponse> getResponseClass() {
        return DeleteArticleResponse.class;
    }
}
