package com.tinet.smartlink.openapi.model.sqc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * 企微表情消息实体
 *
 * @author Ailos
 * @version 1.0
 * @date 2023/3/9
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeComEmotionData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表情类型，1表示gif 2表示png
     */
    private Integer type;

    /**
     * 表情图片宽度
     */
    private Integer width;

    /**
     * 表情图片高度
     */
    private Integer height;

    /**
     * 图片地址
     */
    private String imageUrl;
}
