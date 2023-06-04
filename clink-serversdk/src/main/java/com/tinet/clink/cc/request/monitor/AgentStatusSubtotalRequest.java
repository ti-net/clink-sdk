package com.tinet.clink.cc.request.monitor;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.monitor.AgentStatusSubtotalResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * Class for:
 * 座席当日状态小记
 *
 * @author : Tinet-yinzk
 * @date: 2023/6/4 21:36
 */
public class AgentStatusSubtotalRequest extends AbstractRequestModel<AgentStatusSubtotalResponse> {
    /**
     * 座席工号
     */
    private String cno;

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public AgentStatusSubtotalRequest(){
        super(PathEnum.AgentStatusSubtotal.value(), HttpMethodType.GET);
    }

    @Override
    public Class<AgentStatusSubtotalResponse> getResponseClass() {
        return AgentStatusSubtotalResponse.class;
    }
}
