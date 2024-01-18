package com.tinet.clink.huanxin.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.huanxin.PathEnum;
import com.tinet.clink.huanxin.response.SessionMsgResponse;

public class SessionMsgRequest extends AbstractRequestModel<SessionMsgResponse> {

    private Integer page; //查询页码索引,默认为0

    private Integer size; //每页显示的数据容量，默认为10，最大为50

    private String sessionServiceId ; //会话ID

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSessionServiceId() {
        return sessionServiceId;
    }

    public void setSessionServiceId(String sessionServiceId) {
        this.sessionServiceId = sessionServiceId;
    }

    public SessionMsgRequest() {
        super(PathEnum.QUERY_SESSION_MSGS.value(), HttpMethodType.POST);
    }


    @Override
    public Class<SessionMsgResponse> getResponseClass() {
        return SessionMsgResponse.class;
    }
}
