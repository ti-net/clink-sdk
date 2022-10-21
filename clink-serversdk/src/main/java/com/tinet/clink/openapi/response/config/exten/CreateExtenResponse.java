package com.tinet.clink.openapi.response.config.exten;

import com.tinet.clink.openapi.model.CreateExtenResultModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 新增话机响应
 *
 * @author wangyq
 * @date 2018/10/26
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  CreateExtenResponse extends ResponseModel {

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