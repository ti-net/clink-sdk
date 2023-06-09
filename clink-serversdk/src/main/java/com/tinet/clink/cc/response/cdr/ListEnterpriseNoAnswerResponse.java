package com.tinet.clink.cc.response.cdr;


import com.tinet.clink.cc.model.EnterpriseNoAnswerModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * 获取未接来电记录列表
 *
 * @author yinzk
 * @date 2023/6/9
 **/
public class ListEnterpriseNoAnswerResponse extends ResponseModel {
    /**
     * 未接来电记录
     */
    private List<EnterpriseNoAnswerModel> noAnswers;

    public List<EnterpriseNoAnswerModel> getNoAnswers() {
        return noAnswers;
    }

    public void setNoAnswers(List<EnterpriseNoAnswerModel> noAnswers) {
        this.noAnswers = noAnswers;
    }
}
