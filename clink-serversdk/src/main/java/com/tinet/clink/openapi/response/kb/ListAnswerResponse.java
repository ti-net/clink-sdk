package com.tinet.clink.openapi.response.kb;

import com.tinet.clink.openapi.model.AnswerModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * 答案响应实体
 *
 * @author feizq
 * @date 2022/07/26
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListAnswerResponse extends ResponseModel {

    private List<AnswerModel> answers;

    public List<AnswerModel> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerModel> answers) {
        this.answers = answers;
    }
}
