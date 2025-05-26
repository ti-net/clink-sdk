package com.tinet.clink.livechat.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.livechat.PathEnum;
import com.tinet.clink.livechat.response.ChatClientLoginResponse;
import com.tinet.clink.livechat.response.ChatClientStatusChangeResponse;

/**
 * 客服登录
 *
 * @author wangpw
 * @date 2021年5月14日
 */
public class ChatClientStatusChangeRequest extends AbstractRequestModel<ChatClientStatusChangeResponse> {

    public ChatClientStatusChangeRequest() {
        super(PathEnum.ChatClientStatusChange.value(), HttpMethodType.POST);
    }

    /**
     * 座席工号
     */
    private String cno;
    /**
     * 坐席状态 0：离线 1：在线空闲 2：在线置忙
     */
    private Integer status;

    @Override
    public Class<ChatClientStatusChangeResponse> getResponseClass() {
        return ChatClientStatusChangeResponse.class;
    }

    public void setCno(String cno) {
        putQueryParameter("cno", cno);
        this.cno = cno;
    }


    public String getCno() {
        return cno;
    }

    public void setStatus(Integer status) {
        putQueryParameter("status", status);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
    @Override
    public String toString() {
        return "ChatClientStatusChangeRequest{" +
                "cno='" + cno + '\'' +
                ", status=" + status +
                '}';
    }
}
