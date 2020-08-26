package com.tinet.clink.openapi.request.business;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.Business.ListBusinessResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.HashMap;
import java.util.Map;

/**业务记录查询
 *
 * @author liuhy
 * @date: 2020/7/29
 **/
public class ListBusinessRequest extends AbstractRequestModel<ListBusinessResponse> {


    private Long startTime;

    private Long endTime;

//    private Map<String, String> customize;

    /**
     * 偏移量，范围 0-10000，默认值为 0
     */
    private Integer offset;

    /**
     * 查询条数，范围 10-100，默认值为 10
     */
    private Integer limit;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (offset != null) {
            putQueryParameter("offset", offset);
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


    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
        if (startTime != null) {
            putQueryParameter("startTime", startTime);
        }
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
        if (endTime != null) {
            putQueryParameter("endTime", endTime);
        }
    }

//    public Map<String, String> getCustomize() {
//        return customize;
//    }
//
//    public void setCustomize(Map<String, String> customize) {
//        this.customize = customize;
//        if (customize != null) {
//            putQueryParameter("customize", customize);
//        }
//    }

    public ListBusinessRequest() {
        super(PathEnum.ListBusiness.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ListBusinessResponse> getResponseClass() {
        return ListBusinessResponse.class;
    }


}