package com.tinet.clink.openapi.request.call.control;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.call.control.DtmfResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 发送按键请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  DtmfRequest extends AbstractRequestModel<DtmfResponse> {

    /**
     * 座席工号，4-6 位数字
     */
    private String cno;

    /**
     * 按键值，数字类型的字符串，最大20位，如：0123
     */
    private String digits;

    /**
     * 发送按键方向，取值范围：in、out、all
     */
    private String direction;

    /**
     * 按键持续毫秒数，取值100-500之间，默认值100
     */
    private Integer duration;

    /**
     * 按键之间间隔毫秒数，取值250-1000之间，默认值250
     */
    private Integer gap;

    public DtmfRequest() {
        super(PathEnum.Dtmf.value(), HttpMethodType.POST);
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

    public String getDigits() {
        return digits;
    }

    public void setDigits(String digits) {
        this.digits = digits;
        if (digits != null) {
            putBodyParameter("digits", digits);
        }
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
        if (direction != null) {
            putBodyParameter("direction", direction);
        }
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
        if (duration != null) {
            putBodyParameter("duration", duration);
        }
    }

    public Integer getGap() {
        return gap;
    }

    public void setGap(Integer gap) {
        this.gap = gap;
        if (gap != null) {
            putBodyParameter("gap", gap);
        }
    }

    @Override
    public Class<DtmfResponse> getResponseClass() {
        return DtmfResponse.class;
    }
}
