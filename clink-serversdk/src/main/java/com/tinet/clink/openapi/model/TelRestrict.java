package com.tinet.clink.openapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * 呼叫限制（黑白名单）
 *
 * @author wangyq
 * @date 2018/03/20
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TelRestrict {

    /**
     * 主键id
     */
    private Integer id;

    /**
     *
     */
    private Integer enterpriseId;

    /**
     * 呼叫限制类型 1:黑名单 2:白名单
     */
    private Integer restrictType;

    /**
     * 类型 1:呼入 2:外呼
     */
    private Integer type;

    /**
     * 电话号码
     */
    private String tel;

    /**
     * 电话号码类型 1:单个电话手机不加0固话加区号例如13409876543/01059222999 2:地区例如010/031 3:未知号码
     */
    private Integer telType;

    /**
     * 描述
     */
    private String description;

    /**
     * 记录创建时间
     */
    private Date createTime;

    /**
     * 记录更新时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getRestrictType() {
        return restrictType;
    }

    public void setRestrictType(Integer restrictType) {
        this.restrictType = restrictType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getTelType() {
        return telType;
    }

    public void setTelType(Integer telType) {
        this.telType = telType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}