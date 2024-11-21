package com.tinet.smartlink.openapi.model.sqc;

import lombok.Data;

/**
 * 顺丰对话记录实体
 *
 * @author liuhy
 * @date 2019/8/12 17:54
 */
@Data
public class ChatRecord {

    private String id;

    private Integer role;

    private Long sendTime;

    private Integer messageType;

    private String text;
}
