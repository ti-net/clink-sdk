package com.tinet.smartlink.openapi.model.sqc;

import lombok.Data;

/**
 * @author : chen.wang
 * @description :
 * @date : 2023/5/30
 **/
@Data
public class SemantemeEnumerateFail {
    /**
     * 枚举词id
     */
    private Long enumerateId;

    /**
     * 枚举名称
     */
    private String enumerateName;

    /**
     * 同义词
     */
    private String synonym;

    /**
     * 错误码
     */
    private Integer errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;
}
