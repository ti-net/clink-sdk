package com.tinet.clink.openapi.response.kb;


import com.tinet.clink.openapi.model.StandardQuestionResponseModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 标准问响应实体
 *
 * @author feizq
 * @date 2022/06/15
 **/
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  StandardQuestionResponse extends ResponseModel {

    private StandardQuestionResponseModel standardQuestion;

    public StandardQuestionResponseModel getStandardQuestion() {
        return standardQuestion;
    }

    public void setStandardQuestion(StandardQuestionResponseModel standardQuestion) {
        this.standardQuestion = standardQuestion;
    }
}
