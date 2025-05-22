package com.tinet.smartlink.openapi.response.sqc;

import com.tinet.smartlink.openapi.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 杨冬雨
 * @Date 2022/7/22 10:42
 * @Description 获取质检评分
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListCdrsScoreResponse extends BaseResponse {

    private String result;

    private String message;
}
