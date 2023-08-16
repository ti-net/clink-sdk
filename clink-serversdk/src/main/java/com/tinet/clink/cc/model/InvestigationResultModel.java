package com.tinet.clink.cc.model;

import java.util.Date;

public class InvestigationResultModel {
    private Integer id;
    /**
     * 导航名称
     */
    private String name;

    /**
     * 外显号
     */
    private String[] obClid;

    /**
     * ivrId
     */
    private Integer[] ivrId;

    /**
     * ivr导航
     */
    private String[] ivrName;

    /**
     * 状态 1：启用 0：禁用
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getObClid() {
        return obClid;
    }

    public void setObClid(String[] obClid) {
        this.obClid = obClid;
    }

    public Integer[] getIvrId() {
        return ivrId;
    }

    public void setIvrId(Integer[] ivrId) {
        this.ivrId = ivrId;
    }

    public String[] getIvrName() {
        return ivrName;
    }

    public void setIvrName(String[] ivrName) {
        this.ivrName = ivrName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
