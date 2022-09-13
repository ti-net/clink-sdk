package com.tinet.clink.cc.response.exten;

import com.tinet.clink.cc.model.CreateExtenResultModel;
import com.tinet.clink.core.response.ResponseModel;

/**
 * 新增话机响应
 *
 * @author wangyq
 * @date 2018/10/26
 */
public class CreateExtenResponse extends ResponseModel {

    /**
     * 新增话机返回对象
     */
    private CreateExtenResultModel exten;

    public CreateExtenResultModel getExten() {
        return exten;
    }

    public void setExten(CreateExtenResultModel exten) {
        this.exten = exten;
    }
}