package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.response.GetChatIvrResponse;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;

/**
 * 查询路由导航详情
 */
public class GetChatIvrRequest extends AbstractStatRequest<GetChatIvrResponse> {

    /**
     * 导航ID
     */
    private Integer id;

    public GetChatIvrRequest() {
        super(PathEnum.GetChatIvr.value(), HttpMethodType.POST);
    }

    @Override
    public Class<GetChatIvrResponse> getResponseClass() {
        return GetChatIvrResponse.class;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if (id != null) {
            putQueryParameter("id", id);
        }
    }

}
