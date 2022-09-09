package com.tinet.clink.ticket.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 满意度设置模型
 *
 * @author wangll
 * @date 2018/08/27
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvestigationSettingModel {
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
