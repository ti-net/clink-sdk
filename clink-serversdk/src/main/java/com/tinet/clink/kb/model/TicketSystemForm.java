package com.tinet.clink.kb.model;

import java.util.Date;

/**
 * 工单系统表单记录
 *
 * @author lize
 * @date 2022/6/22
 */
public class TicketSystemForm {

    /**
     * 表单修改类型
     */
    private Integer modifierType;

    /**
     * 表单修改人id
     */
    private Integer modifierId;


    /**
     * 字段名称及字段值
     */
    private TicketHistoryField[] fields;

    /**
     * 系统表单更新时间
     */
    private Date updateTime;

    public Integer getModifierType() { return modifierType; }

    public void setModifierType(Integer modifierType) { this.modifierType = modifierType; }

    public Integer getModifierId() { return modifierId; }

    public void setModifierId(Integer modifierId) { this.modifierId = modifierId; }

    public TicketHistoryField[] getFields() { return fields; }

    public void setFields(TicketHistoryField[] fields) { this.fields = fields; }

    public Date getUpdateTime() { return updateTime; }

    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}
