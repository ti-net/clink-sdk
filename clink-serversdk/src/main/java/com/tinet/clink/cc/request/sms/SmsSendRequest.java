package com.tinet.clink.cc.request.sms;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.sms.SmsSendResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 短信发送请求
 *
 * @author: wangpw
 * @date: 2020/1/2
 */
public class SmsSendRequest extends AbstractRequestModel<SmsSendResponse> {

    /**
     * 座席号-座席号必须为4-6位数字
     */
    private String cno;

    /**
     * 管理员ID
     */
    private Integer adminId;

    /**
     * 电话
     */
    private String tel;

    /**
     * 发送短信内容
     */
    private String content;

    /**
     * 需要关联的客户资料id
     */
    private Integer customerId;

    public SmsSendRequest(){
        super(PathEnum.SmsSend.value(), HttpMethodType.POST);
    }

    @Override
    public Class<SmsSendResponse> getResponseClass() {
        return SmsSendResponse.class;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        putBodyParameter("cno", cno);
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
        putBodyParameter("adminId", adminId);
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
        putBodyParameter("tel", tel);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        putBodyParameter("content", content);
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
        putBodyParameter("customerId", customerId);
    }
}
