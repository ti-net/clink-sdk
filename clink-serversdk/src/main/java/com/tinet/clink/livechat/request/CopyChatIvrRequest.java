package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.response.CopyChatIvrResponse;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;

/**
 * 复制路由导航及其节点
 */
public class CopyChatIvrRequest extends AbstractStatRequest<CopyChatIvrResponse> {

    /**
     * 源导航ID
     */
    private Integer id;

    public CopyChatIvrRequest() {
        super(PathEnum.CopyChatIvr.value(), HttpMethodType.POST);
    }

    @Override
    public Class<CopyChatIvrResponse> getResponseClass() {
        return CopyChatIvrResponse.class;
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
