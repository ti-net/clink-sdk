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
    private String areaGroupName;

    /**
     * 外显号码
     */
    private String[] obClids;


    /**
     * 号码类型，0: 号码；1:动态号码组
     */
    private Integer assignType;

    /**
     * 动态外呼组名称
     * dynamicTelGroupRule.name
     */
    private String dynamicTelGroupName;


    public String getAreaGroupName() {
        return areaGroupName;
    }

    public void setAreaGroupName(String areaGroupName) {
        this.areaGroupName = areaGroupName;
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

    public String getDynamicTelGroupName() {
        return dynamicTelGroupName;
    }

    public void setDynamicTelGroupName(String dynamicTelGroupName) {
        this.dynamicTelGroupName = dynamicTelGroupName;
    }
}
