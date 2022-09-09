package com.tinet.clink.ticket.model;

/**
 * 座席权限对象
 * @author lizy
 * @date 2018/09/06
 */
public class ClientPermission {

    /**
     * 语音转写，0关闭 1开启。默认关闭
     */
    private Integer asr;

    /**
     * 外呼权限， 0关闭 1无限制 2国内长途 3国内本地。默认无限制
     */
    private Integer call;

    /**
     * 通话记录查看权限，1全部 2所属队列 3本座席。默认全部
     */
    private Integer cdr;

    /**
     * 录音试听下载权限， 0关闭 1试听 2试听下载。默认试听下载
     */
    private Integer recordDownload;

    /**
     * 短信发送权限，0关闭 1开启。默认关闭
     */
    private Integer sms;

    /**
     * 通话录音权限，0关闭 1呼入 2外呼 3全部。默认全部
     */
    private Integer record;

    /**
     * 在线客服查看会话记录权限 ，0：全部、1：所属队列、2：本座席； 默认值为 0
     */
    private Integer chat;

    public Integer getAsr() {
        return asr;
    }

    public void setAsr(Integer asr) {
        this.asr = asr;
    }

    public Integer getCall() {
        return call;
    }

    public void setCall(Integer call) {
        this.call = call;
    }

    public Integer getCdr() {
        return cdr;
    }

    public void setCdr(Integer cdr) {
        this.cdr = cdr;
    }

    public Integer getRecordDownload() {
        return recordDownload;
    }

    public void setRecordDownload(Integer recordDownload) {
        this.recordDownload = recordDownload;
    }

    public Integer getSms() {
        return sms;
    }

    public void setSms(Integer sms) {
        this.sms = sms;
    }

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }

    public Integer getChat() {
        return chat;
    }

    public void setChat(Integer chat) {
        this.chat = chat;
    }

    @Override
    public String toString() {
        return "ClientPermission{" +
                "asr=" + asr +
                ", call=" + call +
                ", cdr=" + cdr +
                ", recordDownload=" + recordDownload +
                ", sms=" + sms +
                ", record=" + record +
                ", chat=" + chat +
                '}';
    }
}
