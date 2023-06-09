package com.tinet.clink.cc.request.cdr;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.cdr.ListEnterpriseNoAnswerResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

import java.util.Objects;

/**
 * 获取未接来电列表
 *
 * @author yinzk
 * @date 2023/6/9
 **/
public class ListEnterpriseNoAnswerRequest extends AbstractRequestModel<ListEnterpriseNoAnswerResponse> {

    /**
     * 座席工号
     */
    private String cno;

    public ListEnterpriseNoAnswerRequest() {
        super(PathEnum.ListEnterpriseNoAnswer.value(), HttpMethodType.GET);
    }


    public void setCno(String cno) {
        this.cno = cno;
        if (Objects.nonNull(cno)) {
            this.putQueryParameter("cno", cno);
        }
    }

    public String getCno() {
        return cno;
    }

    @Override
    public Class<ListEnterpriseNoAnswerResponse> getResponseClass() {
        return ListEnterpriseNoAnswerResponse.class;
    }
}

