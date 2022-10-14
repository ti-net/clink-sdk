package com.tinet.clink.kb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 答案返回对象
 *
 * @author feizq
 * @date 2022/06/15
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerResponseModel {

    /**
     * 答案ID
     */
    private Integer id;
    /**
     * 答案
     */
    private String answer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
