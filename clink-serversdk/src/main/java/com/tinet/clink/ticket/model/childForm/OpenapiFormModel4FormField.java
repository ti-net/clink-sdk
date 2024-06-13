package com.tinet.clink.ticket.model.childForm;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 表单字段中的子表单
 *
 * @author linyang
 * @date 2024/4/16
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenapiFormModel4FormField extends OpenapiFormModel {

    /**
     * 字段的类型 1: 单行文本，2: 数值， 3: 邮件，4: IP地址，
     * <p>
     * 5: 多行文本，6: 下拉框，7: 自定义级联，
     * <p>
     * 8: 地区级联，9: 单选框，10: 复选框，11: 日期和时间，
     * <p>
     * 12: 日期，13: 时间，14: 文件，99: 子表单。
     */
    private Integer type;

    /**
     * 该字段是否必填。 0: 非必填，1: 必填。
     */
    private Integer required;

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
}
