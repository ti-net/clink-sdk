package com.tinet.clink.cc.model;

public class OtherSettingsDesModel {

    private String cno;

    /**
     * 声音增益 0-100: 0=AGC:off,(1-100)*300=AGC:320-32000
     * AGC支持:off，1-32768
     */
    private Integer agcRx;

    private Integer agcTx;

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public Integer getAgcRx() {
        return agcRx;
    }

    public void setAgcRx(Integer agcRx) {
        this.agcRx = agcRx;
    }

    public Integer getAgcTx() {
        return agcTx;
    }

    public void setAgcTx(Integer agcTx) {
        this.agcTx = agcTx;
    }
}
