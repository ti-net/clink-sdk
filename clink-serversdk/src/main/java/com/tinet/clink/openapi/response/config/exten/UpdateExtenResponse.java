package com.tinet.clink.openapi.response.config.exten;

import com.tinet.clink.openapi.model.UpdateExtenResultModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * 修改话机响应
 *
 * @author wangyq
 * @date 2018/10/26
 */
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  UpdateExtenResponse extends ResponseModel {

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