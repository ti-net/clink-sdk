package com.tinet.clink.openapi.request.call.manage;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.manage.ThreewayResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 三方请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ThreewayRequest extends AbstractRequestModel<ThreewayResponse> {

    /**
     * 被三方座席号
     */
    private String cno;

    /**
     * 三方类型
     */
    private Integer threewayType;

    /**
     * 三方对象号码
     */
    private String threewayNumber;

    public ThreewayRequest() {
        super(PathEnum.Threeway.value(), HttpMethodType.POST);
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

    public Integer getThreewayType() {
        return threewayType;
    }

    public void setThreewayType(Integer threewayType) {
        this.threewayType = threewayType;
        if (threewayType != null) {
            putBodyParameter("threewayType", threewayType);
        }
    }

    public String getThreewayNumber() {
        return threewayNumber;
    }

    public void setThreewayNumber(String threewayNumber) {
        this.threewayNumber = threewayNumber;
        if (threewayNumber != null) {
            putBodyParameter("threewayNumber", threewayNumber);
        }
    }

    @Override
    public Class<ThreewayResponse> getResponseClass() {
        return ThreewayResponse.class;
    }
}
