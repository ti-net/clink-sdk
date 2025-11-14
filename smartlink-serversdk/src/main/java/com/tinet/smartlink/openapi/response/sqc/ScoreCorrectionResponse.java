package com.tinet.smartlink.openapi.response.sqc;


import com.tinet.smartlink.openapi.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 工单质检推送响应实体
 *
 * @author liuhy
 * @date 2019/8/13 9:51
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ScoreCorrectionResponse extends BaseResponse {

    private String result;
    private String message;
}
