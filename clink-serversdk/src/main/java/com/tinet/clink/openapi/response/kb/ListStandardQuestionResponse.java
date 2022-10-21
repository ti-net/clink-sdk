package com.tinet.clink.openapi.response.kb;

import com.tinet.clink.openapi.model.StandardQuestionModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * 标准问题列表响应实体
 *
 * @author feizq
 * @date 2022/07/27
 **/
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  ListStandardQuestionResponse extends ResponseModel {

    private List<StandardQuestionModel> standardQuestions;

    public List<StandardQuestionModel> getStandardQuestions() {
        return standardQuestions;
    }

    public void setStandardQuestions(List<StandardQuestionModel> standardQuestions) {
        this.standardQuestions = standardQuestions;
    }
}
