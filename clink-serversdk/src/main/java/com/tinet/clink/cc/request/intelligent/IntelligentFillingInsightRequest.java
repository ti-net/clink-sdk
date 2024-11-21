package com.tinet.clink.cc.request.intelligent;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.intelligent.IntelligentFillingInsightResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * @author: wangpw
 * @date: 2022/3/28
 * @description:
 */
public class IntelligentFillingInsightRequest extends AbstractRequestModel<IntelligentFillingInsightResponse> {
    /**
     * 通话记录ID
     */
    private String mainUniqueId;

    /**
     * 模板类型 0 业务记录 1 工单记录， 默认 0
     */
    private Integer type = 0;

    /**
     * 模板名称
     */
    private String formName;

    /**
     * 座席号
     */
    private String cno;

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
        if (mainUniqueId != null) {
            putBodyParameter("mainUniqueId", mainUniqueId);
        }
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
        if (type != null) {
            putBodyParameter("type", type);
        }
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
        if (formName != null) {
            putBodyParameter("formName", formName);
        }
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putBodyParameter("cno", cno);
        }
    }

    public IntelligentFillingInsightRequest() {
        super(PathEnum.IntelligentFillingInsight.value(), HttpMethodType.POST);
    }

    @Override
    public Class<IntelligentFillingInsightResponse> getResponseClass() {
        return IntelligentFillingInsightResponse.class;
    }
}
