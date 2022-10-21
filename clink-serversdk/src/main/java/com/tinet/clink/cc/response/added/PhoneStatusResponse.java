package com.tinet.clink.cc.response.added;


import com.tinet.clink.cc.model.PhoneStatusModel;
import com.tinet.clink.core.response.ResponseModel;

/**
 * @author wangll
 */
public class PhoneStatusResponse extends ResponseModel {


    private PhoneStatusModel phoneStatus;


    public PhoneStatusModel getPhoneStatus() {
        return phoneStatus;
    }

    public void setPhoneStatus(PhoneStatusModel phoneStatus) {
        this.phoneStatus = phoneStatus;
    }
}
