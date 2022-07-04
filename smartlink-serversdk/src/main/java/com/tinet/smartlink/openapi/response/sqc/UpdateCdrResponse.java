package com.tinet.smartlink.openapi.response.sqc;

import com.tinet.smartlink.openapi.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: liurf
 * @Date: 2021/9/22 18:23
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateCdrResponse extends BaseResponse {

    private String result;
    private String message;
    
}
