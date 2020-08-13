package com.tinet.clink.openapi.request.stat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.response.stat.StatHotlineIbResponse;
import com.tinet.clink.openapi.response.stat.StatPreviewObResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;


/**
 * @author Chenjf
 * @date 2020/2/24 15:32
 **/
public class StatPreviewObRequest extends AbstractStatRequest<StatPreviewObResponse> {

    @Override
    public Class<StatPreviewObResponse> getResponseClass() {
        return StatPreviewObResponse.class;
    }

    public StatPreviewObRequest() {
        super(PathEnum.StatPreviewOb.value(), HttpMethodType.POST);
    }

}
