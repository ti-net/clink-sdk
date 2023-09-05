package com.tinet.smartlink.openapi.model.sqc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * 企微图文消息子项实体
 *
 * @author Ailos
 * @version 1.0
 * @date 2023/3/9
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeComNewsItemData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图文消息标题
     */
    private String title;

    /**
     * 图文消息描述
     */
    private String description;

    /**
     * 图文消息点击跳转地址
     */
    private String url;

    /**
     * 图文消息配图的url
     */
    private String picUrl;
}
