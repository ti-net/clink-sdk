package com.tinet.clink.openapi.model;

import java.util.Map;

/**
 * @author Wangyl
 * @date 2020/7/29
 */
public class ChatInvestigation {
    private String mainUniqueId;

    /**
     * 满意度值
     */
    private Integer keys;
    /**
     * 满意度备注
     */
    private String remark;
    /**
     * 满意度标签
     */
    private String[] label;

    /**
     * 满意度描述
     */
    private String description;

    private Long createTime;

    public String getMainUniqueId() {
        return mainUniqueId;
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
    }

    public Integer getKeys() {
        return keys;
    }

    public void setKeys(Integer keys) {
        this.keys = keys;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String[] getLabel() {
        return label;
    }

    public void setLabel(String[] label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
