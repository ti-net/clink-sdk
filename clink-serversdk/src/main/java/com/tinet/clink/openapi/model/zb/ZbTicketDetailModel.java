package com.tinet.clink.openapi.model.zb;

import com.tinet.clink.openapi.model.TicketHistoryForm;
import com.tinet.clink.openapi.model.TicketStatusModel;

/**
 * @author liuhy
 * @date: 2021/11/5
 **/
public class ZbTicketDetailModel {

    private Integer id;

    private TicketStatusModel[] status;

    private Integer level;

    private String auditTime;

    private TicketHistoryForm[] forms;

    private String customerName;

    private String customerTel;

    private String rollBackReason;

    private Integer crmFlag;

    private String sCode;

    private String ticketNumber;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public TicketHistoryForm[] getForms() {
        return forms;
    }

    public void setForms(TicketHistoryForm[] forms) {
        this.forms = forms;
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

    public String getRollBackReason() {
        return rollBackReason;
    }

    public void setRollBackReason(String rollBackReason) {
        this.rollBackReason = rollBackReason;
    }

    public Integer getCrmFlag() {
        return crmFlag;
    }

    public void setCrmFlag(Integer crmFlag) {
        this.crmFlag = crmFlag;
    }

    public String getsCode() {
        return sCode;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
}