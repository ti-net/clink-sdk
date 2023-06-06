package com.tinet.smartlink.openapi.response.sqc;

import com.tinet.smartlink.openapi.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 根据uniqueId批量获取话单详情响应
 *
 * @author liuhongyu
 * @date 2019/12/20
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class ListCdrDetailsByUniqueIdListResponse extends BaseResponse {

    private String result;
    private String message;
}
