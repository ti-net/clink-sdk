package com.tinet.clink.openapi.model;


import java.util.List;

/**
 * @author liuhy
 * @date: 2020/11/19
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  Field {

    /**
     * 字段id
     */
    private Integer id;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段的属性值
     */
    private String property;

    /**
     * 字段的属性值
     */
    private String value;

    /**
     * 字段的类型 1: 单行文本，2: 数值， 3: 邮件，4: IP地址，
     *
     * 5: 多行文本，6: 下拉框，7: 自定义级联，
     *
     * 8: 地区级联，9: 单选框，10: 复选框，11: 日期和时间，
     *
     * 12: 日期，13: 时间，14: 文件，99: 子表单。
     */
    private Integer type;

    /**
     *该字段是否必填。 0: 非必填，1: 必填。
     */
    private Integer required;

    /**
     *子表单对象
     */
    private TicketFormModel childrenForm;

    /**
     *子表单属性（提交时使用）
     */
    private List<Field> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public TicketFormModel getChildrenForm() {
        return childrenForm;
    }

    public void setChildrenForm(TicketFormModel childrenForm) {
        this.childrenForm = childrenForm;
    }

    public List<Field> getChildren() { return children; }

    public void setChildren(List<Field> children) { this.children = children; }
}