package com.tinet.clink.livechat.model;

/**
 * @author wangll
 */
public class EnterprisePauseModel {

    /**
     * id标识
     */
    private Integer id;

    /**
     * 优先级,数字越小优先级越高，在前台座席状态列表展示越靠前
     */
    private Integer sort;

    /**
     * 置忙状态描述
     */
    private String pauseStatus;

    /**
     * 休息状态 0不是 1是
     */
    private Integer isRest;

    /**
     * 默认置忙状态 0不是 1是
     */
    private Integer isDefault;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getPauseStatus() {
        return pauseStatus;
    }

    public void setPauseStatus(String pauseStatus) {
        this.pauseStatus = pauseStatus;
    }

    public Integer getIsRest() {
        return isRest;
    }

    public void setIsRest(Integer isRest) {
        this.isRest = isRest;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}
