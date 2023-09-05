package com.tinet.smartlink.openapi.response.sqc;

import com.tinet.smartlink.openapi.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author： 许成
 * @date： 2021/10/14 15:39
 * @description：
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SaveCdrBusinessPriorityResponse extends BaseResponse {

    private String result;
    private String message;
}
