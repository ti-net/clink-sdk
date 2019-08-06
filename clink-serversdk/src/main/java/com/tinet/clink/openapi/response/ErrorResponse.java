package com.tinet.clink.openapi.response;

import com.tinet.clink.openapi.model.ErrorCode;

/**
 * @author houfc
 */
public class ErrorResponse extends ResponseModel {
    private ErrorCode error;

    public ErrorCode getError() {
        return error;
    }

    public void setError(ErrorCode error) {
        this.error = error;
    }
}
