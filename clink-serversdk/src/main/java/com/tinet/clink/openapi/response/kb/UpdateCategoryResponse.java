package com.tinet.clink.openapi.response.kb;

import com.tinet.clink.openapi.model.CategoryResponseModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 更新分类响应实体
 *
 * @author feizq
 * @date 2022/06/20
 **/
public class UpdateCategoryResponse extends ResponseModel {

    private CategoryResponseModel category;

    public CategoryResponseModel getCategory() {
        return category;
    }

    public void setCategory(CategoryResponseModel category) {
        this.category = category;
    }
}
