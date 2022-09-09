package com.tinet.clink.livechat.model;

/**
 * @author wangll
 */
public class QueueResultModel {

    /**
     * 队列号，在0000 - 9999之间
     */
    private String qno;

    /**
     * 队列名，只允许输入中文、字母、数字、下划线，长度不超过20个字符
     */
    private String name;

    /**
     * 优先级
     */
    private Integer penalty;

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

    public Integer getPenalty() {
        return penalty;
    }

    public void setPenalty(Integer penalty) {
        this.penalty = penalty;
    }
}
