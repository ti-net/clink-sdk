package com.tinet.clink.openapi.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 满意度设置模型
 *
 * @author wangll
 * @date 2018/08/27
 */
@JsonIgnoreProperties(ignoreUnknown = true)
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  InvestigationSettingModel {
    /**
     * 自动执行开关，0：关闭、1：开启
     */
    private Integer auto;
    /**
     * 呼入自动执行开关，0：关闭、1：开启
     */
    private Integer autoIb;
    /**
     * 外呼自动执行开关，0：关闭、1：开启
     */
    private Integer autoOb;


    public Integer getAuto() {
        return auto;
    }

    public void setAuto(Integer auto) {
        this.auto = auto;
    }

    public Integer getAutoIb() {
        return autoIb;
    }

    public void setAutoIb(Integer autoIb) {
        this.autoIb = autoIb;
    }

    public Integer getAutoOb() {
        return autoOb;
    }

    public void setAutoOb(Integer autoOb) {
        this.autoOb = autoOb;
    }

    @Override
    public String toString() {
        return "InvestigationSettingModel{" +
                "auto=" + auto +
                ", autoIb=" + autoIb +
                ", autoOb=" + autoOb +
                '}';
    }
}
