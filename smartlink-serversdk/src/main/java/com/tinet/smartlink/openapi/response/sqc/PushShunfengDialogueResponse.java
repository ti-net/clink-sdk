package com.tinet.smartlink.openapi.response.sqc;


import com.tinet.smartlink.openapi.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 顺丰专项记录质检推送响应实体
 *
 * @author chenjg
 * @date 2020/09/02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PushShunfengDialogueResponse extends BaseResponse {

    // todo zhushi
    private String result;
    private String message;
}
