package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;
import com.tinet.clink.livechat.response.ListChatIvrResponse;

import java.util.List;

/**
 * 按名称、队列分页查询路由导航
 */
public class ListChatIvrRequest extends AbstractStatRequest<ListChatIvrResponse> {

    /**
     * 名称筛选
     */
    private String name;

    /**
     * 队列号
     */
    private List<String> qnos;

    /**
     * 页码，默认1
     */
    private Integer pageIndex;

    /**
     * 每页条数，默认10
     */
    private Integer pageSize;

    public ListChatIvrRequest() {
        super(PathEnum.ListChatIvr.value(), HttpMethodType.POST);
    }

    @Override
    public Class<ListChatIvrResponse> getResponseClass() {
        return ListChatIvrResponse.class;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getQnos() {
        return qnos;
    }

    public void setQnos(List<String> qnos) {
        this.qnos = qnos;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
