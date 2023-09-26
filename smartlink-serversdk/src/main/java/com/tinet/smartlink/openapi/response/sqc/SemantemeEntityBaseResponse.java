package com.tinet.smartlink.openapi.response.sqc;

import com.tinet.smartlink.openapi.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : chen.wang
 * @description :
 * @date : 2023/5/29
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SemantemeEntityBaseResponse extends BaseResponse {

    private Integer code;
    private String result;
    private String errorMsg;
}
