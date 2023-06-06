package com.tinet.smartlink.openapi.model.sqc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeComVoiceData implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 语音消息大小
     */
    private Integer voiceSize;

    /**
     * 语音消息播放长度
     */
    private Integer playLength;

    /**
     * 语音地址
     */
    private String voiceUrl;
}
