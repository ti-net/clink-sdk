package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.response.CreateChatIvrResponse;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;

/**
 * 创建路由导航
 */
public class CreateChatIvrRequest extends AbstractStatRequest<CreateChatIvrResponse> {

    /**
     * 导航名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    public CreateChatIvrRequest() {
        super(PathEnum.CreateChatIvr.value(), HttpMethodType.POST);
    }

    @Override
    public Class<CreateChatIvrResponse> getResponseClass() {
        return CreateChatIvrResponse.class;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
