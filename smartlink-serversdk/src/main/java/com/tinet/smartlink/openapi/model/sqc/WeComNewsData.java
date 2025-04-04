package com.tinet.smartlink.openapi.model.sqc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 企微图文消息实体
 *
 * @author Ailos
 * @version 1.0
 * @date 2023/3/9
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeComNewsData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息子项
     */
    private List<WeComNewsItemData> item;
}
