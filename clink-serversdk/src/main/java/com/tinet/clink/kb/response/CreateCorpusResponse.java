package com.tinet.clink.kb.response;

import com.tinet.clink.core.response.ResponseModel;

/**
 * 创建语料响应实体
 *
 * @author feizq
 * @date 2022/06/15
 **/
public class CreateCorpusResponse extends ResponseModel {

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
