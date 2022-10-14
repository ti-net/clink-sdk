package com.tinet.clink.openapi.response;

import com.tinet.clink.openapi.model.ErrorCode;

/**
 * @author houfc
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ErrorResponse extends ResponseModel {
    private ErrorCode error;

    public ErrorCode getError() {
        return error;
    }

    public void setError(ErrorCode error) {
        this.error = error;
    }
}
