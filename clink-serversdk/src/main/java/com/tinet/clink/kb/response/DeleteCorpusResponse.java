package com.tinet.clink.kb.response;


import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 删除语料响应实体
 *
 * @author feizq
 * @date 2022/06/15
 **/
public class DeleteCorpusResponse extends ResponseModel {

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
