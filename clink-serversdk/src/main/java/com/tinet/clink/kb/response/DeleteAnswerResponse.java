package com.tinet.clink.kb.response;

import com.tinet.clink.core.response.ResponseModel;

/**
 * 删除答案响应实体
 *
 * @author feizq
 * @date 2022/06/15
 **/
public class DeleteAnswerResponse extends ResponseModel {

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
