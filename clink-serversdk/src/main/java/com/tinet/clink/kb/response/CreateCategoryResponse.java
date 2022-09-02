package com.tinet.clink.kb.response;

import com.tinet.clink.openapi.model.CategoryResponseModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 创建分类响应实体
 *
 * @author feizq
 * @date 2022/06/20
 **/
public class CreateCategoryResponse extends ResponseModel {

    private CategoryResponseModel category;

    public CategoryResponseModel getCategory() {
        return category;
    }

    public void setCategory(CategoryResponseModel category) {
        this.category = category;
    }
}
