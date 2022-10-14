package com.tinet.clink.openapi.response.call.webcall;

import com.tinet.clink.openapi.model.CallOutResultModel;
import com.tinet.clink.openapi.model.WebcallResultModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * webcall响应对象
 *
 * @author wangll
 * @date 2020/04/02
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  WebcallResponse extends ResponseModel {

    /**
     * 外呼的返回结果
     */
    private WebcallResultModel result;

    public WebcallResultModel getResult() {
        return result;
    }

    public void setResult(WebcallResultModel result) {
        this.result = result;
    }
}
