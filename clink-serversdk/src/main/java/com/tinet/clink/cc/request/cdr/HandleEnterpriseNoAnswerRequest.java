package com.tinet.clink.cc.request.cdr;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.cdr.HandleEnterpriseNoAnswerResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

import java.util.Objects;

/**
 * 处理未接来电
 *
 * @author yinzk
 * @date 2023/6/9
 **/
public class HandleEnterpriseNoAnswerRequest extends AbstractRequestModel<HandleEnterpriseNoAnswerResponse> {

    /**
     * 未接来电id
     */
    private Integer naId;
    /**
     * 备注
     */
    private String remark;

    public HandleEnterpriseNoAnswerRequest() {
        super(PathEnum.HandleEnterpriseNoAnswer.value(), HttpMethodType.POST);
    }


    public Integer getNaId() {
        return naId;
    }

    public void setNaId(Integer naId) {
        this.naId = naId;
        if (Objects.nonNull(naId)) {
            this.putBodyParameter("naId", naId);
        }
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
        if (Objects.nonNull(remark)) {
            this.putBodyParameter("remark", remark);
        }
    }

    @Override
    public Class<HandleEnterpriseNoAnswerResponse> getResponseClass() {
        return HandleEnterpriseNoAnswerResponse.class;
    }
}

