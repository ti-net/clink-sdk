package com.tinet.smartlink.openapi.model.sqc;

/**
 * 平台类型
 *
 * @author 王大宝
 * @date 2020.03.12
 */
public enum PlatformEnum {
    
    /**
     * clink
     */
    CLINK("clink"),
    /**
     * clink2
     */
    CLINK2("clink2"),
    /**
     * cticloud
     */
    CTICLOUD("cticloud"),
    /**
     * vnc
     */
    VNC("vnc"),
    /**
     * thirdparty
     */
    THIRDPARTY("thirdparty");


    private String value;
    
    PlatformEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
