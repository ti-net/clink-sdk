package com.tinet.clink.kb.request;

import com.tinet.clink.kb.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.kb.response.DeleteArticleResponse;

/**
 * 删除文章请求
 *
 * @author feizq
 * @date 2021/11/29
 **/
public class DeleteArticleRequest extends AbstractRequestModel<DeleteArticleResponse> {

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
