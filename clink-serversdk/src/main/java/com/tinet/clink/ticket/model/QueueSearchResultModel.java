package com.tinet.clink.ticket.model;

/**
 * 队列查询结果对象
 *
 * @author lizy
 * @date 2018/09/13
 */
public class QueueSearchResultModel {


    /**
     * 队列id
     */
    private Integer id;

    /**
     * 队列号，在0000 - 9999之间
     */
    private String qno;

    /**
     * 队列名，只允许输入中文、字母、数字、下划线，长度不超过20个字符
     */
    private String name;

    /**
     * 全渠道座席数
     */
    private Integer omniClientNum;

    /**
     * 呼叫中心座席数
     */
    private Integer callClientNum;

    /**
     * 在线客服座席数
     */
    private Integer chatClientNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOmniClientNum() {
        return omniClientNum;
    }

    public void setOmniClientNum(Integer omniClientNum) {
        this.omniClientNum = omniClientNum;
    }

    public Integer getCallClientNum() {
        return callClientNum;
    }

    public void setCallClientNum(Integer callClientNum) {
        this.callClientNum = callClientNum;
    }

    public Integer getChatClientNum() {
        return chatClientNum;
    }

    public void setChatClientNum(Integer chatClientNum) {
        this.chatClientNum = chatClientNum;
    }

    @Override
    public String toString() {
        return "QueueSearchResultModel{" +
                "id='" + id + '\'' +
                "qno='" + qno + '\'' +
                ", name='" + name + '\'' +
                ", omniClientNum=" + omniClientNum +
                ", callClientNum=" + callClientNum +
                ", chatClientNum=" + chatClientNum +
                '}';
    }
}
