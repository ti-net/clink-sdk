package com.tinet.clink.openapi.request.cdr;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.cdr.CopyCdrIbsResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 数据同步呼入通话记录请求
 *
 * @author huwk
 * @date 2018/10/24
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  CopyCdrIbsRequest extends AbstractRequestModel<CopyCdrIbsResponse> {

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

    public CopyCdrIbsRequest() {
        super(PathEnum.CopyCdrIbs.value(), HttpMethodType.GET);
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

    @Override
    public Class<CopyCdrIbsResponse> getResponseClass() {
        return CopyCdrIbsResponse.class;
    }
}
