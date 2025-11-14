package com.tinet.smartlink.openapi.model.sqc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * 企微混合消息子项实体
 *
 * @author Ailos
 * @version 1.0
 * @date 2023/3/9
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeComMixedItemData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息类型
     * text：文本
     * image：图片
     * revoke：撤回
     * emotion：表情
     * news：图文
     * mixed：混合
     * markdown：MarkDown
     */
    private String type;

    /**
     * 消息对象字符串，与type组合使用
     * text：WeComTextData
     * image：WeComImageData
     * revoke：WeComRevokeData
     * emotion：WeComEmotionData
     * news：WeComNewsData
     * mixed：WeComMixedData
     * markdown：WeComMarkdownData
     */
    private String content;
}
