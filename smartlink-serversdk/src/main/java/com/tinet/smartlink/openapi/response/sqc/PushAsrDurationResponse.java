package com.tinet.smartlink.openapi.response.sqc;

import com.tinet.smartlink.openapi.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * BigBoss对接慧智 推送asr转写结果 统计时长的响应内容
 *
 * @author liuhy
 * @date 2019/10/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PushAsrDurationResponse extends BaseResponse {

    private String result;
    private String message;
}
