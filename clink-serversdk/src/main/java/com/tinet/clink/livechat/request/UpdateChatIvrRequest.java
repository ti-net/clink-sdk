package com.tinet.clink.livechat.request;

import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.response.UpdateChatIvrResponse;
import com.tinet.clink.livechat.request.stat.AbstractStatRequest;

/**
 * 更新路由导航名称或描述
 */
public class UpdateChatIvrRequest extends AbstractStatRequest<UpdateChatIvrResponse> {

    /**
     * 导航ID
     */
    private Integer id;

    /**
     * 名称，空白则不改
     */
    private String name;

    /**
     * 描述，不传不改，传空串清空
     */
    private String description;

    public UpdateChatIvrRequest() {
        super(PathEnum.UpdateChatIvr.value(), HttpMethodType.POST);
    }

    @Override
    public Class<UpdateChatIvrResponse> getResponseClass() {
        return UpdateChatIvrResponse.class;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
