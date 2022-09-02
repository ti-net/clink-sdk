package com.tinet.clink.cc.request.cdr;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.cdr.CopyCdrObDetailsResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 数据同步外呼通话记录明细请求
 *
 * @author huwk
 * @date 2018/10/24
 **/
public class CopyCdrObDetailsRequest extends AbstractRequestModel<CopyCdrObDetailsResponse> {

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


    public CopyCdrObDetailsRequest() {
        super(PathEnum.CopyCdrObDetails.value(), HttpMethodType.GET);
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

    @Override
    public Class<CopyCdrObDetailsResponse> getResponseClass() {
        return CopyCdrObDetailsResponse.class;
    }
}
