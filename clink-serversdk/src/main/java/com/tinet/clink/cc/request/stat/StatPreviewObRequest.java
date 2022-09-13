package com.tinet.clink.cc.request.stat;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.stat.StatPreviewObResponse;
import com.tinet.clink.core.utils.HttpMethodType;


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
