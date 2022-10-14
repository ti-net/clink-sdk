package com.tinet.clink.ticket.model;

/**
 *
 * @author liuhy
 * @date: 2020/9/8
 **/
public class ClientAllModel {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 座席工号
     */
    private String cno;

    /**
     * 座席名称
     */
    private String name;

    /**
     * 座席类型，1：全渠道、2：呼叫中心、3：在线客服
     */
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}