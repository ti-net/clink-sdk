package com.tinet.clink.cc.response.stat;

import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;
import java.util.Map;

/**
 * @author Chenjf
 * @date 2020/2/24 15:32
 **/
public class StatHotlineObAnsweredRateResponse extends PagedResponse {

    private List<Map<String,Object>> statHotlineObAnsweredRate;

    public List<Map<String, Object>> getStatHotlineObAnsweredRate() {
        return statHotlineObAnsweredRate;
    }

    public void setStatHotlineObAnsweredRate(List<Map<String, Object>> statHotlineObAnsweredRate) {
        this.statHotlineObAnsweredRate = statHotlineObAnsweredRate;
    }
}
