package com.tinet.clink.livechat.model;

import java.util.List;

/**
 * 路由导航分页
 */
public class ChatIvrPageModel {

    private int currentPageNo;
    private int pageSize;
    private int totalCount;
    private int totalPageCount;
    private List<ChatIvrModel> data;

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public List<ChatIvrModel> getData() {
        return data;
    }

    public void setData(List<ChatIvrModel> data) {
        this.data = data;
    }
}
