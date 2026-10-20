package com.tinet.clink.livechat.model;

import java.util.List;

/**
 * 渠道接入号与路由导航绑定分页
 */
public class ChannelIvrBindingPageModel {

    private Long total;

    private Integer pageSize;

    private Integer pageIndex;

    private Long pages;

    private Boolean hasPreviousPage;

    private Boolean hasNextPage;

    private List<ChannelIvrBindingModel> list;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Boolean getHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(Boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public Boolean getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(Boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public List<ChannelIvrBindingModel> getList() {
        return list;
    }

    public void setList(List<ChannelIvrBindingModel> list) {
        this.list = list;
    }
}
