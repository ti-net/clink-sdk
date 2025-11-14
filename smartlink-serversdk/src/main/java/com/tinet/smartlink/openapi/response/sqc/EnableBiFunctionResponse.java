package com.tinet.smartlink.openapi.response.sqc;

import com.tinet.smartlink.openapi.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 王大宝
 * @date 2019/4/30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class EnableBiFunctionResponse extends BaseResponse {

    private String result;
    private String message;
}
