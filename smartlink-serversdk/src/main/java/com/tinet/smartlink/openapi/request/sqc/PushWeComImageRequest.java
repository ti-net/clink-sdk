package com.tinet.smartlink.openapi.request.sqc;


import com.tinet.smartlink.openapi.HttpMethodType;
import com.tinet.smartlink.openapi.request.BaseRequest;
import com.tinet.smartlink.openapi.model.sqc.OriginData;
import com.tinet.smartlink.openapi.model.sqc.WeComImageData;
import com.tinet.smartlink.openapi.response.sqc.PushWeComImageResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 推送企微图片消息请求实体
 *
 * @author Ailos
 * @version 1.0
 * @date 2023/3/9
 */
public class PushWeComImageRequest extends BaseRequest<PushWeComImageResponse> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 企业登录名称
     */
    private String accountLoginName;

    /**
     * 企业ID
     */
    private String enterpriseId;

    /**
     * 自定义字段集合
     */
    private List<OriginData> originDataList;

    /**
     * 消息id
     */
    private String msgId;

    /**
     * 消息动作，send(发送消息)/recall(撤回消息)/switch(切换企业日志)
     */
    private String action;

    /**
     * 消息发送方id
     */
    private String from;

    /**
     * 消息发送方名称
     */
    private String fromName;

    /**
     * 消息接收方id列表
     */
    private List<String> toList;

    /**
     * 消息接收方名称列表
     */
    private List<String> toNameList;

    /**
     * 群聊消息的群id，如果是单聊则为空
     */
    private String roomId;

    /**
     * 群聊消息的群名称，如果是单聊则为空
     */
    private String roomName;

    /**
     * 消息发送时间戳，utc时间，ms单位
     */
    private Long msgTime;

    /**
     * 消息对象
     */
    private WeComImageData image;

    public String getAccountLoginName() {
        return accountLoginName;
    }

    public void setAccountLoginName(String accountLoginName) {
        this.accountLoginName = accountLoginName;
        if (accountLoginName != null) {
            putBodyParameter("accountLoginName", accountLoginName);
        }
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
        if (enterpriseId != null) {
            putBodyParameter("enterpriseId", enterpriseId);
        }
    }

    public List<OriginData> getOriginDataList() {
        return originDataList;
    }

    public void setOriginDataList(List<OriginData> originDataList) {
        this.originDataList = originDataList;
        if (originDataList != null) {
            putBodyParameter("originDataList", originDataList);
        }
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
        if (msgId != null) {
            putBodyParameter("msgId", msgId);
        }
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
        if (action != null) {
            putBodyParameter("action", action);
        }
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
        if (from != null) {
            putBodyParameter("from", from);
        }
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
        if (fromName != null) {
            putBodyParameter("fromName", fromName);
        }
    }

    public List<String> getToList() {
        return toList;
    }

    public void setToList(List<String> toList) {
        this.toList = toList;
        if (toList != null) {
            putBodyParameter("toList", toList);
        }
    }

    public List<String> getToNameList() {
        return toNameList;
    }

    public void setToNameList(List<String> toNameList) {
        this.toNameList = toNameList;
        if (toNameList != null) {
            putBodyParameter("toNameList", toNameList);
        }
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
        if (roomId != null) {
            putBodyParameter("roomId", roomId);
        }
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
        if (roomName != null) {
            putBodyParameter("roomName", roomName);
        }
    }

    public Long getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(Long msgTime) {
        this.msgTime = msgTime;
        if (msgTime != null) {
            putBodyParameter("msgTime", msgTime);
        }
    }

    public WeComImageData getImage() {
        return image;
    }

    public void setImage(WeComImageData image) {
        this.image = image;
        if (image != null) {
            putBodyParameter("image", image);
        }
    }

    public PushWeComImageRequest() {
        super("/sqc/wecom/pushImageData", HttpMethodType.POST);
    }

    @Override
    public Class<PushWeComImageResponse> getResponseClass() {
        return PushWeComImageResponse.class;
    }
}
