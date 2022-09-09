package com.tinet.clink.cc.response.restrict;

import com.tinet.clink.openapi.model.TelRestrictSearchResultModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * @author libin
 * @date 2021-12-13 1:45 下午
 */
public class ListTelRestrictResponse extends ResponseModel {

    private  Integer pageNumber;

    private Integer pageSize;

    private List<TelRestrictSearchResultModel> restrictTels;

    private Integer totalCount;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<TelRestrictSearchResultModel> getRestrictTels() {
        return restrictTels;
    }

    public void setRestrictTels(List<TelRestrictSearchResultModel> restrictTels) {
        this.restrictTels = restrictTels;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
