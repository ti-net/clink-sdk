package com.tinet.clink.openapi.model;


/**
 * @author wangli
 * @date 2022-05-30 5:15 PM
 */
public class TrunkModel {

    /**
     * 区号
     */
    private String areaCode;

    /**
     * 中继号码
     */
    private String trunkNumber;

    /**
     * 省份
     */
    private String province;

    /**
     * 号码对应城市
     */
    private String city;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getTrunkNumber() {
        return trunkNumber;
    }

    public void setTrunkNumber(String trunkNumber) {
        this.trunkNumber = trunkNumber;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
