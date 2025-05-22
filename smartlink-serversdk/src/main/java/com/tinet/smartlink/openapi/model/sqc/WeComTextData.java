package com.tinet.smartlink.openapi.model.sqc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * 企微文本消息实体
 *
 * @author Ailos
 * @version 1.0
 * @date 2023/3/9
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeComTextData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文本内容
     */
    private String content;
}
