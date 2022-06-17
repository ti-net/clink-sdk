package com.tinet.clink.openapi.response.kb;

import com.tinet.clink.openapi.model.AnswerResponseModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author feizq
 * @date 2022/06/15
 **/
public class CreateAnswerResponse extends ResponseModel {

    private AnswerResponseModel answer;

    public AnswerResponseModel getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerResponseModel answer) {
        this.answer = answer;
    }
}
