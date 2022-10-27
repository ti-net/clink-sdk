package com.tinet.clink.openapi.response.ticket;

import com.tinet.clink.openapi.model.TicketFormModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author lize
 * @date: 2022/4/1
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  GetFormDetailResponse extends ResponseModel {


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