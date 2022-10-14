package com.tinet.clink.openapi.model;

/**
 * 原始满意度提交内容选项
 *
 * @author wangtao
 * @date 2019/06/04
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ChatInvestigationOptionModel {

    /**
     * 选项名称
     */
    private String name;

    /**
     * 星级 1->一星 2->二星
     */
    private Integer star;

    /**
     * 满意度标签
     */
    private String[] label;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String[] getLabel() {
        return label;
    }

    public void setLabel(String[] label) {
        this.label = label;
    }
}
