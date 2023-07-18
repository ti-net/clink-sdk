package com.tinet.clink.cc.request.cdr;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.cdr.CopyCdrObsResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * 数据同步外呼通话记录请求
 *
 * @author huwk
 * @date 2018/10/24
 **/
public class CopyCdrObsRequest extends AbstractRequestModel<CopyCdrObsResponse> {

    /**
     * 指定同步某一天的通话记录。格式：yyyyMMdd, 例如：20180816
     */
    private String date;

    /**
     * 每次请求返回的参数，作为下次请求的必须参数。
     * for 循环第一次请求不需要传递此参数
     */
    private String scrollId;

    /**
     * 查询条数，范围 10-100。默认值为 10
     */
    private Integer limit;

    /**
     * 是否隐藏号码。
     * 0: 不隐藏，1: 中间四位，2: 最后八位，3: 全部号码，4: 最后四位。默认值为 0
     */
    private Integer hiddenType;

    /**
     * 需要展示的字段
     * tagNames
     */
    private String[] fields;

    public CopyCdrObsRequest() {
        super(PathEnum.CopyCdrObs.value(), HttpMethodType.GET);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        if (date != null) {
            putQueryParameter("date", date);
        }
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
        if (scrollId != null) {
            putQueryParameter("scrollId", scrollId);
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (limit != null) {
            putQueryParameter("limit", limit);
        }
    }

    public Integer getHiddenType() {
        return hiddenType;
    }

    public void setHiddenType(Integer hiddenType) {
        this.hiddenType = hiddenType;
        if (hiddenType != null) {
            putQueryParameter("hiddenType", hiddenType);
        }
    }



    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
        if (fields != null && fields.length > 0) {
            putQueryParameter("fields", String.join(",", fields));
        }
    }

    @Override
    public Class<CopyCdrObsResponse> getResponseClass() {
        return CopyCdrObsResponse.class;
    }
}
