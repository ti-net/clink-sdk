package com.tinet.clink.openapi.response.kb;

import com.tinet.clink.openapi.model.CategoryResponseModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 创建分类响应实体
 *
 * @author feizq
 * @date 2022/06/20
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  CreateCategoryResponse extends ResponseModel {

    private CategoryResponseModel category;

    public CategoryResponseModel getCategory() {
        return category;
    }

    public void setCategory(CategoryResponseModel category) {
        this.category = category;
    }
}
