package com.tinet.clink.cc.request.data;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.data.DataManagerResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * @author xieyan
 * @date 2023/10/25 16:02
 * @description
 * @Version 1.0
 */
public class DataManagerRequest extends AbstractRequestModel<DataManagerResponse> {

    /**
     * 操作方式，set:设置缓存数据 get:读取缓存数据
     */
    private String method;


    /**
     * 键值，长度不超过256个字节
     */
    private String key;

    /**
     * 键对应值，长度不超过4096个字节。1个汉字是3字节。 method=set时必选
     */
    private String value;

    /**
     * 有效期秒数，默认3600秒，取值范围0-86400之间 method=set时有效
     */
    private Integer expired;

    /**
     * 可访问次数，默认值10000 取值范围1-10000之间 method=set时有效
     */
    private Integer accessLimit;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
        if (method != null) {
            putQueryParameter("method", method);
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
        if (key != null) {
            putQueryParameter("key", key);
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        if (value != null) {
            putQueryParameter("value", value);
        }
    }

    public Integer getExpired() {
        return expired;
    }

    public void setExpired(Integer expired) {
        this.expired = expired;
        if (expired != null) {
            putQueryParameter("expired", expired);
        }
    }

    public Integer getAccessLimit() {
        return accessLimit;
    }

    public void setAccessLimit(Integer accessLimit) {
        this.accessLimit = accessLimit;
        if (accessLimit != null) {
            putQueryParameter("accessLimit", accessLimit);
        }
    }

    public DataManagerRequest() {
        super(PathEnum.DataManager.value(), HttpMethodType.POST);
    }

    @Override
    public Class<DataManagerResponse> getResponseClass() {
        return DataManagerResponse.class;
    }
}
