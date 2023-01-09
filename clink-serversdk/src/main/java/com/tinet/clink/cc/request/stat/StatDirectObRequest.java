package com.tinet.clink.cc.request.stat;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.stat.StatDirectObResponse;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * 外呼报表-主叫外呼
 * @author libin
 * @date 2023-01-03 11:39
 */
public class StatDirectObRequest extends AbstractStatRequest<StatDirectObResponse> {

    @Override
    public Class<StatDirectObResponse> getResponseClass() {
        return StatDirectObResponse.class;
    }

    public StatDirectObRequest() {
        super(PathEnum.StatDirectOb.value(), HttpMethodType.POST);
    }
}
