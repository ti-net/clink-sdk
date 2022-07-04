package com.tinet.clink.openapi.response.kb;

import com.tinet.clink.openapi.response.ResponseModel;

/**
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
