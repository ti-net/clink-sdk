package com.tinet.smartlink.openapi.model.sqc;

import lombok.Data;

/**
 * @author : chen.wang
 * @description :
 * @date : 2023/5/30
 **/
@Data
public class SemantemeEnumerateDetail {
    /**
     * 枚举id
     */
    private String enumerateId;

    /**
     * 枚举名称
     */
    private String enumerateName;

    /**
     * 同义词
     */
    private String synonym;
}
