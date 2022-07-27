package com.tinet.clink.openapi.response.kb;

import com.tinet.clink.openapi.model.AnswerModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * @author feizq
 * @date 2022/07/26
 **/
public class ListAnswerResponse extends ResponseModel {

    private List<AnswerModel> answers;

    public List<AnswerModel> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerModel> answers) {
        this.answers = answers;
    }
}
