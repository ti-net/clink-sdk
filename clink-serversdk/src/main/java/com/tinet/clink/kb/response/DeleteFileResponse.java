package com.tinet.clink.kb.response;

import com.tinet.clink.core.response.ResponseModel;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author 周先康
 * @create 2023/3/7 13:35
 */
public class DeleteFileResponse extends ResponseModel {

    String[] result;

    public String[] getResult() {
        return result;
    }

    public void setResult(String[] result) {
        this.result = result;
    }
}
