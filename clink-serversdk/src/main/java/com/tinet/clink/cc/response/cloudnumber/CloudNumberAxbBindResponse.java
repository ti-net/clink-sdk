package com.tinet.clink.cc.response.cloudnumber;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinet.clink.cc.model.CloudNumberAxbBindModel;
import com.tinet.clink.core.response.ResponseModel;

/**
 * Class For:
 * 云手机ax 绑定
 *
 * @author Tinet-yinzk
 * @date 2023/11/28 20:46
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudNumberAxbBindResponse extends ResponseModel {
    /**
     * ax 绑定结果
     */
    private String message;

    /**
     * ax 绑定结果
     */
    private CloudNumberAxbBindModel result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CloudNumberAxbBindModel getResult() {
        return result;
    }

    public void setResult(CloudNumberAxbBindModel result) {
        this.result = result;
    }
}
