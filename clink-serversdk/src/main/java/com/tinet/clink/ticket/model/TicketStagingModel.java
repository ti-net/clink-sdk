package com.tinet.clink.ticket.model;

/**
 * @author wangli
 * @date 2024-03-08 14:58
 */
public class TicketStagingModel extends TicketSaveModel{

    private Integer creatorCode;

    private String creatorValue;

    public Integer getCreatorCode() {
        return creatorCode;
    }

    public void setCreatorCode(Integer creatorCode) {
        this.creatorCode = creatorCode;
    }

    public String getCreatorValue() {
        return creatorValue;
    }

    public void setCreatorValue(String creatorValue) {
        this.creatorValue = creatorValue;
    }
}
