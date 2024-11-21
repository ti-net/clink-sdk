package com.tinet.smartlink.openapi.response.sqc;


import com.tinet.smartlink.openapi.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 创建录音文件转写response
 * @author 王大宝
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class JumpUserCheckResponse extends BaseResponse {

    /**
     * token
     */
    private String token;

}
