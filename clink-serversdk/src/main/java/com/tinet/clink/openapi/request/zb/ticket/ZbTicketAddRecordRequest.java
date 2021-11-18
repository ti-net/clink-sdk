package com.tinet.clink.openapi.request.zb.ticket;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.model.Field;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.zb.ticket.ZbTicketAddRecordResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author liuhy
 * @date: 2021/11/3
 **/
public class ZbTicketAddRecordRequest extends AbstractRequestModel<ZbTicketAddRecordResponse> {


    /**
     * 工单id
     */
    private Integer id;

    /**
     * 工单状态
     */
    private Integer status;

    /**
     * 工单自定义字段数组
     */
    private Field[] fields;

    public ZbTicketAddRecordRequest() {
        super(PathEnum.ZbTicketAddRecord.value(), HttpMethodType.POST);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;

        if (id != null) {
            putBodyParameter("id", id);
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;

        if (status != null) {
            putBodyParameter("status", status);
        }
    }

    public Field[] getFields() {
        return fields;
    }

    public void setFields(Field[] fields) {
        this.fields = fields;

        if (fields != null) {
            putBodyParameter("fields", fields);
        }
    }

    @Override
    public Class<ZbTicketAddRecordResponse> getResponseClass() {
        return ZbTicketAddRecordResponse.class;
    }
}