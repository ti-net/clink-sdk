package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.ListChannelIvrBindingResponse;

/**
 * 查询渠道接入号与路由导航绑定
 */
public class ListChannelIvrBindingRequest extends AbstractStatRequest<ListChannelIvrBindingResponse> {

    /**
     * 渠道类型，取枚举名称，如 WEB、WEIXIN
     */
    private String contactType;

    /**
     * 接入号ID。传入时按该接入号精确查询；不传则分页查询该渠道下的接入号
     */
    private String accessId;

    /**
     * 每页条数，默认 10
     */
    private Integer pageSize;

    /**
     * 当前页，从 1 开始，默认 1。指定 accessId 时不参与查询
     */
    private Integer pageIndex;

    public ListChannelIvrBindingRequest() {
        super(PathEnum.ListChannelIvrBinding.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ListChannelIvrBindingResponse> getResponseClass() {
        return ListChannelIvrBindingResponse.class;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
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
}
