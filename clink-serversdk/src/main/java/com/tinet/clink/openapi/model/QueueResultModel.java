package com.tinet.clink.openapi.model;

/**
 * @author wangll
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  QueueResultModel {

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
