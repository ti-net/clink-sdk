package com.tinet.smartlink.openapi.response.sqc;


import com.tinet.smartlink.openapi.model.sqc.AgentDetail;
import com.tinet.smartlink.openapi.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * @author wenjd
 * @date 2019/04/01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AgentDetailResponse extends BaseResponse {

    private List<AgentDetail> result;
    private String message;

}

