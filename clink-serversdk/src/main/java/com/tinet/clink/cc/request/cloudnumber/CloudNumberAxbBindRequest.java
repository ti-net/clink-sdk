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
public class CloudNumberAxbBindRequest extends AbstractRequestModel<CloudNumberAxbBindResponse> {

    /**
     * 绑定，1-绑定，0-解绑
     */
    private Integer bind;
    /**
     * 座席工号
     */
    private String cno;
    /**
     * 座席的虚拟工作卡号码
     */
    private String telX;
    /**
     * 客户号码
     */
    private String telB;
    /**
     * AX绑定后得到的认证ID；bind=1时，必填
     */
    private String authId;
    /**
     * AXB绑定后得到的绑定id；bind=0时，必填
     */
    private String bindId;
    /**
     * 有效期，单位：s；bind=1时，必填
     */
    private Integer expiration;

    public CloudNumberAxbBindRequest() {
        super(PathEnum.CloudNumberAxbBind.value(), HttpMethodType.POST);
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

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (Objects.nonNull(cno)) {
            this.putBodyParameter("cno", cno);
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

    public String getTelB() {
        return telB;
    }

    public void setTelB(String telB) {
        this.telB = telB;
        if (Objects.nonNull(telB)) {
            this.putBodyParameter("telB", telB);
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

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
        if (Objects.nonNull(bindId)) {
            this.putBodyParameter("bindId", bindId);
        }
    }

    public Integer getExpiration() {
        return expiration;
    }

    public void setExpiration(Integer expiration) {
        this.expiration = expiration;
        if (Objects.nonNull(expiration)) {
            this.putBodyParameter("expiration", expiration);
        }
    }

    @Override
    public Class<CloudNumberAxbBindResponse> getResponseClass() {
        return CloudNumberAxbBindResponse.class;
    }
}
