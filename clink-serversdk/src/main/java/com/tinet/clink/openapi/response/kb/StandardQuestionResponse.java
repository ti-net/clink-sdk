package com.tinet.clink.openapi.response.kb;


import com.tinet.clink.openapi.model.StandardQuestionResponseModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 标准问响应实体
 *
 * @author feizq
 * @date 2022/06/15
 **/
public class StandardQuestionResponse extends ResponseModel {

    private StandardQuestionResponseModel standardQuestion;

    public StandardQuestionResponseModel getStandardQuestion() {
        return standardQuestion;
    }

    public void setStandardQuestion(StandardQuestionResponseModel standardQuestion) {
        this.standardQuestion = standardQuestion;
    }
}
