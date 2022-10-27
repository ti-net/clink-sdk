package com.tinet.clink.cc.response.webcall;

import com.tinet.clink.cc.model.WebcallResultModel;
import com.tinet.clink.core.response.ResponseModel;

/**
 * webcall响应对象
 *
 * @author wangll
 * @date 2020/04/02
 */
public class WebcallResponse extends ResponseModel {

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
