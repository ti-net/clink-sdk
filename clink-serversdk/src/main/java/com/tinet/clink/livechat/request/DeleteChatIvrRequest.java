package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.response.DeleteChatIvrResponse;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;

/**
 * 删除路由导航
 */
public class DeleteChatIvrRequest extends AbstractStatRequest<DeleteChatIvrResponse> {

    /**
     * 导航ID
     */
    private Integer id;

    public DeleteChatIvrRequest() {
        super(PathEnum.DeleteChatIvr.value(), HttpMethodType.POST);
    }

    @Override
    public Class<DeleteChatIvrResponse> getResponseClass() {
        return DeleteChatIvrResponse.class;
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
