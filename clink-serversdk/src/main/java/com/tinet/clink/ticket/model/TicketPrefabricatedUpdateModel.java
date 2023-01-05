package com.tinet.clink.ticket.model;

import java.util.Date;

/**
 * @author wangli
 * @date 2023-01-04 14:15
 */
public class TicketPrefabricatedUpdateModel {

    /**
     * 工单 Id (id和externalId 二者必有一个)
     */
    private Integer id;

    /**
     * 工单 外部 Id (id和externalId 二者必有一个)
     */
    private String externalId;

    /**
     * 操作人ID
     */
    private Integer operatorId;

    /**
     * 表单数据
     */
    private TicketFormModel form;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public TicketFormModel getForm() {
        return form;
    }

    public void setForm(TicketFormModel form) {
        this.form = form;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
