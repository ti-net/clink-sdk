package com.tinet.clink.openapi.request.call.control;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.control.CalloutResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 外呼请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  CalloutRequest extends AbstractRequestModel<CalloutResponse> {

    /**
     * 座席工号，4-6 位数字
     */
    private String cno;

    /**
     * 客户电话，固话类型需要添加区号，手机类型不加 0，固话带分机以 “-” 分隔。
     * 如果企业开启号码隐藏功能，可从弹屏事件中获取customerNumberKey的值，进行外呼
     */
    private String customerNumber;

    /**
     * 座席临时绑定号码，暂时只支持手机号
     */
    private String bindTel;

    /**
     * 客户侧外显号码
     */
    private String clid;

    /**
     * 外显类型，0：外显号码、1：接口标识
     */
    private Integer type;

    /**
     * 接口外呼标识，按照标识配置的外显号码进行外显。如果同时传入clid和clidGroup，clid优先级高
     */
    private String clidGroup;

    /**
     * 呼叫座席侧超时时间，取值范围 5-60s，默认 30s
     */
    private Integer clientTimeout;

    /**
     * 呼叫客户侧超时时间，取值范围 5-60s，默认 45s
     */
    private Integer customerTimeout;

    /**
     * 唯一id
     */
    private String requestUniqueId;

    /**
     * 用户自定义变量，json格式字符串，例如：{"key":"value"}，需要进行urlEncode。传入的值会在通话记录中进行展示
     */
    private String userField;

    public CalloutRequest() {
        super(PathEnum.Callout.value(), HttpMethodType.POST);
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (cno != null) {
            putBodyParameter("cno", cno);
        }
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
        if (customerNumber != null) {
            putBodyParameter("customerNumber", customerNumber);
        }
    }

    public String getBindTel() {
        return bindTel;
    }

    public void setBindTel(String bindTel) {
        this.bindTel = bindTel;
        if (bindTel != null) {
            putBodyParameter("bindTel", bindTel);
        }
    }

    public String getClid() {
        return clid;
    }

    public void setClid(String clid) {
        this.clid = clid;
        if (clid != null) {
            putBodyParameter("clid", clid);
        }
    }

    public String getClidGroup() {
        return clidGroup;
    }

    public void setClidGroup(String clidGroup) {
        this.clidGroup = clidGroup;
        if (clidGroup != null) {
            putBodyParameter("clidGroup", clidGroup);
        }
    }

    public Integer getClientTimeout() {
        return clientTimeout;
    }

    public void setClientTimeout(Integer clientTimeout) {
        this.clientTimeout = clientTimeout;
        if (clientTimeout != null) {
            putBodyParameter("clientTimeout", clientTimeout);
        }
    }

    public Integer getCustomerTimeout() {
        return customerTimeout;
    }

    public void setCustomerTimeout(Integer customerTimeout) {
        this.customerTimeout = customerTimeout;
        if (customerTimeout != null) {
            putBodyParameter("customerTimeout", customerTimeout);
        }
    }

    public String getUserField() {
        return userField;
    }

    public void setUserField(String userField) {
        this.userField = userField;
        if (userField != null) {
            putBodyParameter("userField", userField);
        }
    }

    public String getRequestUniqueId() {
        return requestUniqueId;
    }

    public void setRequestUniqueId(String requestUniqueId) {
        this.requestUniqueId = requestUniqueId;

        if (requestUniqueId != null) {
            putBodyParameter("requestUniqueId", requestUniqueId);
        }
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;

        if (type != null) {
            putBodyParameter("type", type);
        }
    }

    @Override
    public Class<CalloutResponse> getResponseClass() {
        return CalloutResponse.class;
    }
}
