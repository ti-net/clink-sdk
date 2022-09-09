package com.tinet.clink.kb.model;

import java.util.List;

/**
 * 座席查询结果对象
 *
 * @author lizy
 * @date 2018/09/11
 */
public class ClientSearchResultModel {

    /**
     * 座席id
     */
    private Integer id;

    /**
     * 座席号
     */
    private String cno;

    /**
     * 座席名称
     */
    private String name;

    /**
     * 区号
     */
    private String areaCode;

    /**
     * 绑定电话
     */
    private String bindTel;

    /**
     * 座席角色，0普通座席 1班长座席。
     */
    private Integer role;

    /**
     * 启用状态 0停用 1启用。
     */
    private Integer active;

    /**
     * 座席状态 0离线 1离线
     */
    private Integer status;

    /**
     * 号码隐藏类型，0不隐藏 1全局。
     */
    private Integer hiddenTel;

    /**
     * 座席类型，1：全渠道、2：呼叫中心、3：在线客服
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 座席crm id
     */
    private String crmId;

    /**
     * 队列号
     */
    private List<String> qnos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getBindTel() {
        return bindTel;
    }

    public void setBindTel(String bindTel) {
        this.bindTel = bindTel;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getHiddenTel() {
        return hiddenTel;
    }

    public void setHiddenTel(Integer hiddenTel) {
        this.hiddenTel = hiddenTel;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getCrmId() {
        return crmId;
    }

    public void setCrmId(String crmId) {
        this.crmId = crmId;
    }

    public List<String> getQnos() {
        return qnos;
    }

    public void setQnos(List<String> qnos) {
        this.qnos = qnos;
    }

    @Override
    public String toString() {
        return "ClientSearchResultModel{" +
                "id=" + id +
                ", cno='" + cno + '\'' +
                ", name='" + name + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", bindTel='" + bindTel + '\'' +
                ", role=" + role +
                ", active=" + active +
                ", status=" + status +
                ", hiddenTel=" + hiddenTel +
                ", type=" + type +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", crmId='" + crmId + '\'' +
                ", qnos=" + qnos +
                '}';
    }
}
