package com.tinet.clink.openapi.model;

/**
 * 智能外显配置
 *
 * @author lizy
 * @date 2018/09/06
 */
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  ClidArea {

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
