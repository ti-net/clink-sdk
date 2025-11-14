package com.tinet.smartlink.openapi.response.sqc;

import com.tinet.smartlink.openapi.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 根据质检时间查询ES数据响应
 *
 * @author liurf
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class PostListCdrSqcDetailsResponse extends BaseResponse {


    private String result;

    private String message;
}
