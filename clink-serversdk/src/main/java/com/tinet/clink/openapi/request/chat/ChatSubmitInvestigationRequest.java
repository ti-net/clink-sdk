package com.tinet.clink.openapi.request.chat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.model.ChatInvestigationSaveOption;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.ChatSubmitInvestigationResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.Arrays;

/**
 * 访客提交满意度评价
 *
 * @author wangpw
 * @date 2021年5月14日
 */
public class ChatSubmitInvestigationRequest extends AbstractRequestModel<ChatSubmitInvestigationResponse> {

    public ChatSubmitInvestigationRequest() {
        super(PathEnum.ChatSubmitInvestigation.value(), HttpMethodType.POST);
    }

    /**
     * 主会话标识
     */
    private String sessionId;

    /**
     * 评价信息
     */
    private ChatInvestigationSaveOption[] options;

    /**
     * 评价备注
     */
    private String remark;

    /**
     * 解决状态
     */
    private Integer solve;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
        putQueryParameter("sessionId", sessionId);
    }

    public ChatInvestigationSaveOption[] getOptions() {
        return options;
    }

    public void setOptions(ChatInvestigationSaveOption[] options) {
        this.options = options;
        putQueryParameter("options", options);
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
        putQueryParameter("remark", remark);
    }

    public Integer getSolve() {
        return solve;
    }

    public void setSolve(Integer solve) {
        this.solve = solve;
        putQueryParameter("solve", solve);
    }

    @Override
    public Class<ChatSubmitInvestigationResponse> getResponseClass() {
        return ChatSubmitInvestigationResponse.class;
    }

    @Override
    public String toString() {
        return "ChatSubmitInvestigationRequest{" +
                "sessionId='" + sessionId + '\'' +
                ", options=" + Arrays.toString(options) +
                ", remark='" + remark + '\'' +
                ", solve=" + solve +
                "} " + super.toString();
    }
}
