package com.tinet.clink.cc.request.cdr;


import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.cdr.HandleOrderCallbackResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

import java.util.Objects;

/**
 * 处理预约回呼
 *
 * @author yinzk
 * @date 2023/6/9
 **/
public class HandleOrderCallbackRequest extends AbstractRequestModel<HandleOrderCallbackResponse> {

    /**
     * 预约回呼记录id
     */
    private Integer id;
    /**
     * 备注
     */
    private String cno;

    public HandleOrderCallbackRequest() {
        super(PathEnum.HandleOrderCallback.value(), HttpMethodType.POST);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if (Objects.nonNull(id)) {
            this.putBodyParameter("id", id);
        }

    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (Objects.nonNull(cno)) {
            this.putBodyParameter("cno", cno);
        }
    }

    @Override
    public Class<HandleOrderCallbackResponse> getResponseClass() {
        return HandleOrderCallbackResponse.class;
    }
}

