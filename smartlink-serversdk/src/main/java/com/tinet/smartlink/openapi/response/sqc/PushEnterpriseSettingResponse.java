package com.tinet.smartlink.openapi.response.sqc;

import com.tinet.smartlink.openapi.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 王大宝
 * @date 2019/4/30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PushEnterpriseSettingResponse extends BaseResponse {

    private String result;
    private String message;
}
