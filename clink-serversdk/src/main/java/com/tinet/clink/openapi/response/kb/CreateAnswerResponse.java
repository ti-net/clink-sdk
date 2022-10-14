package com.tinet.clink.openapi.response.kb;

import com.tinet.clink.openapi.model.AnswerResponseModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 创建答案响应实体
 *
 * @author feizq
 * @date 2022/06/15
 **/
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  CreateAnswerResponse extends ResponseModel {

    private AnswerResponseModel answer;

    public AnswerResponseModel getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerResponseModel answer) {
        this.answer = answer;
    }
}
