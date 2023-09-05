package com.tinet.smartlink.openapi.response.sqc;

import com.tinet.smartlink.openapi.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * BigBoss对接慧智平台asr计费 响应
 *
 * @author liuhy
 * @date 2019/10/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PushAsrResponse extends BaseResponse {

    private String result;
    private String message;
}
