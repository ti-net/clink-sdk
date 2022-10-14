package com.tinet.clink.openapi.response.kb;

import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 删除分类响应实体
 *
 * @author feizq
 * @date 2022/06/20
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DeleteCategoryResponse extends ResponseModel {

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
