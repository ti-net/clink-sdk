package com.tinet.clink.cc.response.cdr;

import com.tinet.clink.core.response.ResponseModel;

/**
 * Class for:
 * 处理未接来电 响应
 *
 * @author : Tinet-yinzk
 * @date : 2023/6/4 23:02
 */
public class HandleEnterpriseNoAnswerResponse extends ResponseModel {

    /**
     * 操作结果
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
