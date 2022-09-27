package com.tinet.clink.openapi.request.ticket;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.GetFormDetailResponse;
import com.tinet.clink.openapi.response.ticket.GetTicketDetailResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 获取表单详情
 *
 * @author lize
 * @date: 2022/4/1
 **/
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  GetFormDetailRequest extends AbstractRequestModel<GetFormDetailResponse> {

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