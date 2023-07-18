package com.tinet.clink.cc.model;

/**
 * 智能外显配置
 *
 * @author lizy
 * @date 2018/09/06
 */
public class ClidArea {

    /**
     * 地区组
     */
    private Integer areaGroupId;

    /**
     * 外显号码
     */
    private String[] obClids;


    /**
     * 号码类型，0: 号码；1:动态号码组
     */
    private Integer assignType;

    /**
     * 动态外呼组id
     * dynamicTelGroupRule.id
     */
    private Integer dynamicTelGroupId;


    public Integer getAreaGroupId() {
        return areaGroupId;
    }

    public void setAreaGroupId(Integer areaGroupId) {
        this.areaGroupId = areaGroupId;
    }

    public String[] getObClids() {
        return obClids;
    }

    public void setObClids(String[] obClids) {
        this.obClids = obClids;
    }

    public Integer getAssignType() {
        return assignType;
    }

    public void setAssignType(Integer assignType) {
        this.assignType = assignType;
    }

    public Integer getDynamicTelGroupId() {
        return dynamicTelGroupId;
    }

    public void setDynamicTelGroupId(Integer dynamicTelGroupId) {
        this.dynamicTelGroupId = dynamicTelGroupId;
    }
}
