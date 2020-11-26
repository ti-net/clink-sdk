package com.tinet.clink.openapi.model;

/**工单标签对象
 * @author liuhy
 * @date: 2020/9/8
 **/
public class Tag {

    /**
     * 标签名称
     */
    private String name;

    /**
     * 背景颜色
     */
    private Integer color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }
}