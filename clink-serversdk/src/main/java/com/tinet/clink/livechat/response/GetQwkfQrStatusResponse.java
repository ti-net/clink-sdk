package com.tinet.clink.livechat.response;

import com.tinet.clink.core.response.ResponseModel;
import com.tinet.clink.livechat.model.QwkfQrStatusModel;

/**
 * 企微二维码状态响应
 */
public class GetQwkfQrStatusResponse extends ResponseModel {

    /**
     * 二维码状态
     */
    private QwkfQrStatusModel qr;


    public QwkfQrStatusModel getQr() {
        return qr;
    }

    public void setQr(QwkfQrStatusModel qr) {
        this.qr = qr;
    }

}
