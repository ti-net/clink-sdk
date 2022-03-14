package com.tinet.clink.openapi.model;

/**
 * 座席查询结果对象
 *
 * @author lizy
 * @date 2018/09/11
 */
public class ClientSearchResultModel {

    /**
     * 座席id
     */
    private Integer id;

    /**
     * 座席号
     */
    private String cno;

    /**
     * 座席名称
     */
    private String name;

    /**
     * bip
     */
    private String bip;


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

    public String getBip() {
        return bip;
    }

    public void setBip(String bip) {
        this.bip = bip;
    }

    @Override
    public String toString() {
        return "ClientSearchResultModel{" +
                "id='" + id + '\'' +
                "cno='" + cno + '\'' +
                ", name='" + name + '\'' +
                ", bip='" + bip +
                '}';
    }
}
