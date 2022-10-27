package com.tinet.clink.cc.request.manage;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.manage.SpyResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 监听请求
 *
 * @author huwk
 * @date 2018/10/30
 **/
public class SpyRequest extends AbstractRequestModel<SpyResponse> {

    /**
     * 被监听座席号
     */
    private String cno;

    /**
     * 监听类型
     */
    private Integer spyType;

    /**
     * 监听对象号码
     */
    private String spyNumber;

    public SpyRequest() {
        super(PathEnum.Spy.value(), HttpMethodType.POST);
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

    public Integer getSpyType() {
        return spyType;
    }

    public void setSpyType(Integer spyType) {
        this.spyType = spyType;
        if (spyType != null) {
            putBodyParameter("spyType", spyType);
        }
    }

    public String getSpyNumber() {
        return spyNumber;
    }

    public void setSpyNumber(String spyNumber) {
        this.spyNumber = spyNumber;
        if (spyNumber != null) {
            putBodyParameter("spyNumber", spyNumber);
        }
    }

    @Override
    public Class<SpyResponse> getResponseClass() {
        return SpyResponse.class;
    }
}
