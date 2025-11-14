package com.tinet.smartlink.openapi.model.sqc;

import lombok.Data;


/**
 * @author： 许成
 * @date： 2021/10/14 10:50
 * @description：
 */
@Data
public class BusinessPriority {
    /**
     * 业务优先级的枚举key
     */
    private String value;
    /**
     * 业务优先级的枚举翻译
     */
    private String label;
}
