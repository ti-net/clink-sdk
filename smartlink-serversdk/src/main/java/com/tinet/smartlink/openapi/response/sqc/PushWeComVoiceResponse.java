package com.tinet.smartlink.openapi.response.sqc;

import com.tinet.smartlink.openapi.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 推送企微语音消息响应实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PushWeComVoiceResponse extends BaseResponse {
    private String result;
    private String message;
}
