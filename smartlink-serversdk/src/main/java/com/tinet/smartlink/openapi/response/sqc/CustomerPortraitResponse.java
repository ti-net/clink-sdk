package com.tinet.smartlink.openapi.response.sqc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.smartlink.openapi.response.PagedResponse;
import com.tinet.smartlink.openapi.model.sqc.CustomerPortrait;
import lombok.Data;

import java.util.List;

/**
 * Class for:
 * <p>
 * 客户画像外放接口，
 *
 * @author yinzk
 * @date 2020/8/24
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerPortraitResponse extends PagedResponse {
    /**
     * 请求id
     */
    private String requestId;
    /**
     * 分页结果
     */
    private List<CustomerPortrait> data;
}
