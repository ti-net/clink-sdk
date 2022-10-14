package com.tinet.clink.kb.response;

import com.tinet.clink.openapi.model.ChangedStandardQuestionModel;
import com.tinet.clink.core.response.PagedResponse;

import java.util.List;

/**
 * 标准问题分页列表响应实体
 *
 * @author feizq
 * @date 2022/09/22
 **/
public class PageStandardQuestionResponse extends PagedResponse {

    List<ChangedStandardQuestionModel> standardQuestions;

    public List<ChangedStandardQuestionModel> getStandardQuestions() {
        return standardQuestions;
    }

    public void setStandardQuestions(List<ChangedStandardQuestionModel> standardQuestions) {
        this.standardQuestions = standardQuestions;
    }
}
