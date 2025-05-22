package com.tinet.smartlink.openapi.model.sqc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * 企微撤回消息实体
 *
 * @author Ailos
 * @version 1.0
 * @date 2023/3/9
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeComRevokeData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 撤回的消息id
     */
    private String preMsgId;
}
