package com.tinet.clink.ticket.response;

import com.tinet.clink.openapi.model.TicketFormModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author lize
 * @date: 2022/4/1
 **/
public class GetSysFormDetailResponse extends ResponseModel {


    /**
     * 工单模板表单实体对象
     */
    private TicketFormModel form;

    public TicketFormModel getForm() {
        return form;
    }

    public void setForm(TicketFormModel form) {
        this.form = form;
    }
}