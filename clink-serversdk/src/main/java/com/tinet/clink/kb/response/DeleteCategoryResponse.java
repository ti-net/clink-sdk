package com.tinet.clink.kb.response;
import com.tinet.clink.core.response.ResponseModel;

/**
 * 删除分类响应实体
 *
 * @author feizq
 * @date 2022/06/20
 **/
public class DeleteCategoryResponse extends ResponseModel {

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
