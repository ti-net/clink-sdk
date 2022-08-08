package com.tinet.clink.openapi.response.kb;


import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 删除标准问响应实体
 *
 * @author feizq
 * @date 2022/06/15
 **/
public class DeleteStandardQuestionResponse extends ResponseModel {

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
