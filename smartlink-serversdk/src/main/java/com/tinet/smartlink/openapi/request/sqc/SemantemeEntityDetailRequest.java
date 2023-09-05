package com.tinet.smartlink.openapi.request.sqc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.response.sqc.SemantemeEntityBaseResponse;
import lombok.Data;

/**
 * @author : chen.wang
 * @description :
 * @date : 2023/5/29
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SemantemeEntityDetailRequest  extends BaseRequest<SemantemeEntityBaseResponse> {

    /**
     * 实体id
     */
    private Long entityId;

    /**
     * 企业id
     */
    private String enterpriseId;

    public SemantemeEntityDetailRequest() {
        super("/sqc/semanteme/entity/get", HttpMethodType.POST);
    }

    @Override
    public Class<SemantemeEntityBaseResponse> getResponseClass() {
        return SemantemeEntityBaseResponse.class;
    }
}
