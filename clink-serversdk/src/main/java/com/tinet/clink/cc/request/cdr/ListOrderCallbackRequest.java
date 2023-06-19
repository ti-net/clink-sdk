package com.tinet.clink.cc.request.cdr;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.cdr.ListOrderCallbackResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

import java.util.Objects;

/**
 * 查询预约回呼记录列表请求
 *
 * @author yinzk
 * @date 2023/6/9
 **/
public class ListOrderCallbackRequest extends AbstractRequestModel<ListOrderCallbackResponse> {

    /**
     * 座席工号
     */
    private String cno;
    /**
     * 是否需要客户名称,0:不需要,1:需要
     */
    private Integer needCustomerName;

    public ListOrderCallbackRequest() {
        super(PathEnum.ListOrderCallback.value(), HttpMethodType.GET);
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

    public Integer getNeedCustomerName() {
        return needCustomerName;
    }

    public void setNeedCustomerName(Integer needCustomerName) {
        this.needCustomerName = needCustomerName;
        if (Objects.nonNull(needCustomerName)) {
            this.putQueryParameter("needCustomerName", needCustomerName);
        }
    }

    @Override
    public Class<ListOrderCallbackResponse> getResponseClass() {
        return ListOrderCallbackResponse.class;
    }
}

