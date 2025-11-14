package com.tinet.smartlink.openapi.model.sqc;

import lombok.Data;

/**
 * @author : chen.wang
 * @description :
 * @date : 2023/5/29
 **/
@Data
public class SemantemeEnumerateAddUpdate {

    /**
     * 枚举id
     * 新增时不传
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
}
