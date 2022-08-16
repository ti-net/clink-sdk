package com.tinet.clink.openapi.request.ticket;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.UpdateFieldPropertyResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 更新工单字段选项值请求
 *
 * @author wangli
 * @date 2022-08-09 4:26 下午
 */
public class UpdateFieldPropertyRequest extends AbstractRequestModel<UpdateFieldPropertyResponse> {

    /**
     * 字段ID
     */
    private Integer id;

    /**
     * 字段的property
     */
    private String property;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if (id != null) {
            putBodyParameter("id", id);
        }
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
        if (property != null) {
            putBodyParameter("property", property);
        }
    }

    public UpdateFieldPropertyRequest() {
        super(PathEnum.updateTicketFieldProperty.value(), HttpMethodType.POST);
    }

    @Override
    public Class<UpdateFieldPropertyResponse> getResponseClass() {
        return UpdateFieldPropertyResponse.class;
    }

}
