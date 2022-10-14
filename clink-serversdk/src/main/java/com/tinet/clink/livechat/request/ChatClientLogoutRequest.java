package com.tinet.clink.livechat.request;

import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.response.ChatClientLogoutResponse;

/**
 * 客服登出
 *
 * @author wangpw
 * @date 2021年5月14日
 */
public class ChatClientLogoutRequest extends AbstractRequestModel<ChatClientLogoutResponse> {

    public ChatClientLogoutRequest() {
        super(PathEnum.ChatClientLogout.value(), HttpMethodType.POST);
    }

    /**
     * 座席工号
     */
    private String cno;

    @Override
    public Class<ChatClientLogoutResponse> getResponseClass() {
        return ChatClientLogoutResponse.class;
    }

    public void setCno(String cno) {
        putQueryParameter("cno", cno);
        this.cno = cno;
    }


    public String getCno() {
        return cno;
    }

    @Override
    public String toString() {
        return "ChatClientLogoutRequest{" +
                "cno='" + cno + '\'' +
                "} " + super.toString();
    }
}
