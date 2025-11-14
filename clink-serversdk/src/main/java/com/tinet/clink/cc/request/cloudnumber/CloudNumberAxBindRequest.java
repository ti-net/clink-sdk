package com.tinet.clink.cc.request.cloudnumber;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.cloudnumber.CloudNumberAxbBindResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

import java.util.Objects;

/**
 * Class For:
 * 云手机ax 绑定
 *
 * @author Tinet-yinzk
 * @date 2023/11/28 20:41
 */
public class CloudNumberAxBindRequest extends AbstractRequestModel<CloudNumberAxbBindResponse> {


    /**
     * 绑定，1-绑定，0-解绑
     */
    private Integer bind;
    /**
     * 座席工号
     */
    private String cno;
    /**
     * 是否需要下发短信启动实名认证， bind=1时，必填；
     * 0-否，通过省侧其他渠道实名。
     * 1-是，通过短信链接发起实名。
     */
    private Integer authType;
    /**
     * 座席号码
     */
    private String telA;

    /**
     * 座席的虚拟工作卡号码
     */
    private String telX;
    /**
     * AX绑定后得到的认证ID；bind=1时，必填
     */
    private String authId;

    public CloudNumberAxBindRequest() {
        super(PathEnum.CloudNumberAxBind.value(), HttpMethodType.POST);
    }

    public Integer getBind() {
        return bind;
    }

    public void setBind(Integer bind) {
        this.bind = bind;
        if (Objects.nonNull(bind)) {
            this.putBodyParameter("bind", bind);
        }
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
        if (Objects.nonNull(authType)) {
            this.putBodyParameter("authType", authType);
        }
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
        if (Objects.nonNull(authId)) {
            this.putBodyParameter("authId", authId);
        }
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (Objects.nonNull(cno)) {
            this.putBodyParameter("cno", cno);
        }
    }

    public String getTelA() {
        return telA;
    }

    public void setTelA(String telA) {
        this.telA = telA;
        if (Objects.nonNull(telA)) {
            this.putBodyParameter("telA", telA);
        }
    }

    public String getTelX() {
        return telX;
    }

    public void setTelX(String telX) {
        this.telX = telX;
        if (Objects.nonNull(telX)) {
            this.putBodyParameter("telX", telX);
        }
    }

    @Override
    public Class<CloudNumberAxbBindResponse> getResponseClass() {
        return CloudNumberAxbBindResponse.class;
    }
}
