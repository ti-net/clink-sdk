package com.tinet.clink.openapi.request.chat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.ChatClientLogoutResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

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
