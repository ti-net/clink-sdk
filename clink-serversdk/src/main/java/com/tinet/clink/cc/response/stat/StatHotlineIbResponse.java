package com.tinet.clink.cc.response.stat;

import com.tinet.clink.core.response.PagedResponse;

import java.util.List;
import java.util.Map;

/**
 * @author Chenjf
 * @date 2020/2/24 15:32
 **/
public class StatHotlineIbResponse extends PagedResponse {

    private List<Map<String,Object>> statHotlineIb;

    public List<Map<String, Object>> getStatHotlineIb() {
        return statHotlineIb;
    }

    public void setStatHotlineIb(List<Map<String, Object>> statHotlineIb) {
        this.statHotlineIb = statHotlineIb;
    }
}
