package com.tinet.smartlink.openapi.response.sqc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.smartlink.openapi.model.sqc.ScrollResult;
import com.tinet.smartlink.openapi.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScrollResponse extends BaseResponse {
    private List<ScrollResult> result;
    private String message;
    private String cursor;
    private Long total;
}
