package com.tinet.clink.cc.model;

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

    /**
     * 满意度发起方式 1：座席主动发起、2：系统邀评、3: 访客主动发起
     */
    private Integer invitationInitiator;


    public Integer getInvitationInitiator() {
        return invitationInitiator;
    }

    public void setInvitationInitiator(Integer invitationInitiator) {
        this.invitationInitiator = invitationInitiator;
    }

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
