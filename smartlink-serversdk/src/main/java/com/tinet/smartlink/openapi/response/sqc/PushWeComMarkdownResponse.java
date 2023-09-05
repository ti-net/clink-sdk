package com.tinet.smartlink.openapi.response.sqc;


import com.tinet.smartlink.openapi.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 推送企微Markdown消息响应实体
 *
 * @author Ailos
 * @version 1.0
 * @date 2023/3/9
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PushWeComMarkdownResponse extends BaseResponse {
    private String result;
    private String message;
}
