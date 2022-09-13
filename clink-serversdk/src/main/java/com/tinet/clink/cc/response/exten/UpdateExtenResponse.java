package com.tinet.clink.cc.response.exten;

import com.tinet.clink.cc.model.UpdateExtenResultModel;
import com.tinet.clink.core.response.ResponseModel;

/**
 * 修改话机响应
 *
 * @author wangyq
 * @date 2018/10/26
 */
public class UpdateExtenResponse extends ResponseModel {

    /**
     * 修改话机返回对象
     */
    private UpdateExtenResultModel exten;

    public UpdateExtenResultModel getExten() {
        return exten;
    }

    public void setExten(UpdateExtenResultModel exten) {
        this.exten = exten;
    }
}