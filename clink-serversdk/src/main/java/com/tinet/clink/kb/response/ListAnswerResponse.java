package com.tinet.clink.kb.response;

import com.tinet.clink.kb.model.AnswerModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * 答案响应实体
 *
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
