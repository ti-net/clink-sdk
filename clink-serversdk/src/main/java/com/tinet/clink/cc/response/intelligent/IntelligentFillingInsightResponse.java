package com.tinet.clink.cc.response.intelligent;


import com.tinet.clink.core.response.ResponseModel;

/**
 * @author: wangpw
 * @date: 2022/3/28
 * @description:
 */
public class IntelligentFillingInsightResponse extends ResponseModel {

    private Integer templateId;

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    @Override
    public String toString() {
        return "IntelligentFillingInsightResponse{" +
                "templateId=" + templateId +
                '}';
    }
}
