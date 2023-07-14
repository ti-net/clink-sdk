package com.tinet.clink.cc.model;

/**
 * 话机列表返回对象
 *
 * @author wangyq
 * @date 2018/10/26
 */
public class ListBindedExtensResultModel {

    /**
     * 话机号码
     */
    private String extenNumber;

    /**
     * 话机类型，1: 分机， 2: 软电话
     */
    private Integer type;

    public String getExtenNumber() {
        return extenNumber;
    }

    public void setExtenNumber(String extenNumber) {
        this.extenNumber = extenNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}