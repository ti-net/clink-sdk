package com.tinet.clink.openapi.response.ticket;

import com.tinet.clink.openapi.model.TicketFieldModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * 获取工单字段列表响应
 *
 * @author wangli
 * @date 2022-08-09 3:58 下午
 */
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  ListFieldsResponse  extends ResponseModel {

    /**
     * 工单字段集合
     */
    List<TicketFieldModel> fields;

    public List<TicketFieldModel> getFields() {
        return fields;
    }

    public void setTickets(List<TicketFieldModel> fields) {
        this.fields = fields;
    }

}
