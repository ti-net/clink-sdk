package com.tinet.clink.openapi.request.config.client;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.config.client.ListClientsResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 查询座席列表请求
 *
 * @author lizy
 * @date 2018/10/29
 */
public class ListClientsRequest extends AbstractRequestModel<ListClientsResponse> {

    /**
     * 队列号
     */
    private String qno;

    /**
     * 是否激活，1: 激活；0: 未激活
     */
    private Integer active;

    /**
     * 外显号码
     */
    private String clid;

    /**
     * 绑定电话
     */
    private String bindTel;

    /**
     * 偏移量，默认值为 0，最大范围 10000
     */
    private Integer offset;

    /**
     * 查询记录条数，默认值为 10，最大范围 100
     */
    private Integer limit;

    public ListClientsRequest() {
        super(PathEnum.ListClients.value(), HttpMethodType.GET);
    }

    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
        if (qno != null) {
            putQueryParameter("qno", qno);
        }
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
        if (active != null) {
            putQueryParameter("active", active);
        }
    }

    public String getClid() {
        return clid;
    }

    public void setClid(String clid) {
        this.clid = clid;
        if (clid != null) {
            putQueryParameter("clid", clid);
        }
    }

    public String getBindTel() {
        return bindTel;
    }

    public void setBindTel(String bindTel) {
        this.bindTel = bindTel;
        if (bindTel != null) {
            putQueryParameter("bindTel", bindTel);
        }
    }

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

    @Override
    public Class<ListClientsResponse> getResponseClass() {
        return ListClientsResponse.class;
    }


}
