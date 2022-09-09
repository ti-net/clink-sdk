package com.tinet.clink.livechat.model;

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
}
