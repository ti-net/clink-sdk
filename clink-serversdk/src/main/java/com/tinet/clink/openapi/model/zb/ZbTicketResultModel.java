package com.tinet.clink.openapi.model.zb;

import com.tinet.clink.openapi.model.TicketHistoryForm;
import com.tinet.clink.openapi.model.TicketStatusModel;

/**
 *
 * @author liuhy
 * @date: 2021/11/5
 **/
public class ZbTicketResultModel {

    private Integer id;

    private TicketStatusModel[] status;

    private TicketHistoryForm[] form;

    private String customerName;

    private String customerTel;

    private Integer level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TicketStatusModel[] getStatus() {
        return status;
    }

    public void setStatus(TicketStatusModel[] status) {
        this.status = status;
    }

    public TicketHistoryForm[] getForm() {
        return form;
    }

    public void setForm(TicketHistoryForm[] form) {
        this.form = form;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }
}