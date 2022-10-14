package com.tinet.clink.openapi.response.added;

import com.tinet.clink.openapi.model.PhoneStatusModel;
import com.tinet.clink.openapi.response.ResponseModel;

/**
 * @author wangll
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  PhoneStatusResponse extends ResponseModel {


    private PhoneStatusModel phoneStatus;


    public PhoneStatusModel getPhoneStatus() {
        return phoneStatus;
    }

    public void setPhoneStatus(PhoneStatusModel phoneStatus) {
        this.phoneStatus = phoneStatus;
    }
}
