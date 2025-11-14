package com.tinet.clink.crm.model;

import java.util.Date;

/**
 * 查询企微群客户资料返回数据模型
 *
 * @author yangcr
 * @date 2024/07/30
 */
public class GroupCustomerResultModel {

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 群客户名称
     */
    private String name;

    /**
     * 访客标识
     */
    private String[] visitorIds;

    /**
     * 自定义字段
     */
    private CustomizeField[] customize;

    /**
     * 客户标签
     */
    private Integer[] labelIds;

    /**
     * 修改人类型，0：座席、1：管理员
     */
    private Integer modifierType;

    /**
     * 修改人，新增时创建人即修改人
     */
    private Integer modifierId;

    /**
     * 修改人姓名
     */
    private String modifierName;

    /**
     * 记录修改时间
     */
    private Date updateTime;

    /**
     * 记录创建时间
     */
    private Date createTime;

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

    public String[] getVisitorIds() {
        return visitorIds;
    }

    public void setVisitorIds(String[] visitorIds) {
        this.visitorIds = visitorIds;
    }

    public CustomizeField[] getCustomize() {
        return customize;
    }

    public void setCustomize(CustomizeField[] customize) {
        this.customize = customize;
    }

    public Integer[] getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(Integer[] labelIds) {
        this.labelIds = labelIds;
    }

    public Integer getModifierType() {
        return modifierType;
    }

    public void setModifierType(Integer modifierType) {
        this.modifierType = modifierType;
    }

    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
