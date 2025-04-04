package com.tinet.smartlink.openapi.model.sqc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 王大宝
 * @date 2019/5/28
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutoItem  implements Serializable {
    /**
     * 质检项
     */
    private String item;
    /**
     * 评语
     */
    private String message;
    /**
     * 原始的转写文本
     */
    private String originText;
    /**
     * 质检项记录的类型 0：质检记录； 1：追加记录
     */
    private Integer recordType;
    /**
     * 纠正后的转写文本
     */
    private String reviseText;
    /**
     * 质检项纠正类型  0：未纠正（默认） 1：转写原因 2：其他原因 3：多话术原因 4：转写纠正， 5：话者分离错误
     */
    private Integer reviseType;
    /**
     * 评分操作：-10 表示减十分
     */
    private Integer score;
    /**
     * 文本所在侧 0：座席侧，1：客户侧
     */
    private Integer side;
    /**
     * 开始时间时间，单位： ms 该句话的开始时间
     */
    private Long timestamp;
    /**
     * 结束时间时间，单位： ms  该句话的结束时间
     */
    private Long endTime;
    /**
     * 类型： 8 blockly新生评分质检项类型
     */
    private Integer type = 8;
    /**
     * 质检项初始分值 也就是质检项的权重
     */
    private Integer weight;
    /**
     * 纠正评语
     */
    private String commit;




}
