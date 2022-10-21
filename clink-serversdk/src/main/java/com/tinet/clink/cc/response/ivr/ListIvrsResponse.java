package com.tinet.clink.cc.response.ivr;

import com.tinet.clink.cc.model.IvrModel;
import com.tinet.clink.core.response.ResponseModel;

import java.util.List;

/**
 * 查询语音导航列表响应
 *
 * @author huwk
 * @date 2018/11/15
 **/
public class ListIvrsResponse extends ResponseModel {

    /**
     * ivr对象列表
     */
    private List<IvrModel> ivrs;

    public List<IvrModel> getIvrs() {
        return ivrs;
    }

    public void setIvrs(List<IvrModel> ivrs) {
        this.ivrs = ivrs;
    }
}
