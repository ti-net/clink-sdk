package com.tinet.clink.kb.model;

/**
 * 队列成员对象
 *
 * @author lizy
 * @date 2018/09/13
 */
public class QueueMemberModel {

    /**
     * 队列号
     */
    private String cno;

    /**
     * 惩罚值
     */
    private Integer penalty;

    /**
     * 座席类型，1：全渠道、2：呼叫中心、3：在线客服
     */
    private Integer type;


    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public Integer getPenalty() {
        return penalty;
    }

    public void setPenalty(Integer penalty) {
        this.penalty = penalty;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "QueueMemberModel{" +
                "cno='" + cno + '\'' +
                ", penalty=" + penalty +
                ", type=" + type +
                '}';
    }
}
