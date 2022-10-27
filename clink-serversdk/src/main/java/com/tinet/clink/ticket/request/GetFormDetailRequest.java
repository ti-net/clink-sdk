package com.tinet.clink.ticket.request;

import com.tinet.clink.ticket.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.ticket.response.GetFormDetailResponse;

/**
 * 获取表单详情
 *
 * @author lize
 * @date: 2022/4/1
 **/
public class GetFormDetailRequest extends AbstractRequestModel<GetFormDetailResponse> {

    /**
     * 表单id
     */
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if (id != null) {
            putQueryParameter("id", id);
        }
    }

    public GetFormDetailRequest() {
        super(PathEnum.GetFormDetail.value(), HttpMethodType.GET);
    }

    @Override
    public Class<GetFormDetailResponse> getResponseClass() {
        return GetFormDetailResponse.class;
    }
}