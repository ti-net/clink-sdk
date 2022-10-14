package com.tinet.clink.cc.request.queue;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.model.QueueMemberModel;
import com.tinet.clink.cc.response.queue.UpdateQueueResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


import java.util.List;

/**
 * 更新队列请求
 *
 * @author lizy
 * @date 2018/10/25
 */
public class UpdateQueueRequest extends AbstractRequestModel<UpdateQueueResponse> {
    /**
     * 队列号，在0000 - 9999之间
     */
    private String qno;

    /**
     * 队列名，只允许输入中文、字母、数字、下划线，长度不超过20个字符
     */
    private String name;

    /**
     * 最大等待数，设置范围0-999，0表示不做任何限制。默认为5
     */
    private Integer maxWait;


    /**
     * 座席超时时间，取值范围20-60秒。默认25秒
     */
    private Integer memberTimeout;

    /**
     * 队列超时时间，取值范围30-600秒。默认600秒
     */
    private Integer queueTimeout;


    /**
     * 允许呼入队列 0 不允许 1 允许
     */
    private Integer ibAllowed;

    /**
     * 呼叫策略, 1顺序 2轮选 3平均 4随机 5技能优先 6最长空闲时间。默认轮选
     */
    private Integer strategy;

    /**
     * 列优先级，取值范围1-10。默认值为1
     * 座席属于多个队列时，优先级高的队列的来电将优先接听；数值越高，优先级越高
     */
    private Integer weight;

    /**
     * 整理时长，取值范围3-300秒，整理期间座席不接受新的呼叫。默认15秒
     */
    private Integer wrapupTime;

    /**
     * 服务水平秒数，取值范围10 ~ 30秒，低于此时间内接听的认为是高服务水平。默认15秒
     */
    private Integer serviceLevel;

    /**
     * 1: 置忙，2: 通话中，4: 振铃，8: 无效，16: 整理
     * 如选多种状态则传对应数值的和，如选置忙和通话中，值为 3。默认值为 0，即都未选中
     */
    private Integer joinEmpty;

    /**
     * 座席号和座席在该队列的惩罚值
     */
    private List<QueueMemberModel> queueMembers;

    /**
     * 在线客服分配策略
     */
    private Integer chatStrategy;

    /**
     * 在线客服最大排队数
     */
    private Integer chatMaxWait;

    /**
     * 在线客服排队位置推送(小于该位置则推送)
     */
    private Integer chatLocation;

    /**
     * 语音报号
     */
    private Integer sayCno;

    /**
     * 队列是否支持vip级别 0:不支持 1:支持
     */
    private Integer vipSupport;


    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
        if (qno != null) {
            putBodyParameter("qno", qno);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (name != null) {
            putBodyParameter("name", name);
        }
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
        if (maxWait != null) {
            putBodyParameter("maxWait", maxWait);
        }
    }

    public Integer getMemberTimeout() {
        return memberTimeout;
    }

    public void setMemberTimeout(Integer memberTimeout) {
        this.memberTimeout = memberTimeout;
        if (memberTimeout != null) {
            putBodyParameter("memberTimeout", memberTimeout);
        }
    }

    public Integer getQueueTimeout() {
        return queueTimeout;
    }

    public void setQueueTimeout(Integer queueTimeout) {
        this.queueTimeout = queueTimeout;
        if (queueTimeout != null) {
            putBodyParameter("queueTimeout", queueTimeout);
        }
    }

    public Integer getIbAllowed() {
        return ibAllowed;
    }

    public void setIbAllowed(Integer ibAllowed) {
        this.ibAllowed = ibAllowed;
        if (ibAllowed != null) {
            putBodyParameter("ibAllowed", ibAllowed);
        }
    }

    public Integer getStrategy() {
        return strategy;
    }

    public void setStrategy(Integer strategy) {
        this.strategy = strategy;
        if (strategy != null) {
            putBodyParameter("strategy", strategy);
        }
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
        if (weight != null) {
            putBodyParameter("weight", weight);
        }
    }

    public Integer getWrapupTime() {
        return wrapupTime;
    }

    public void setWrapupTime(Integer wrapupTime) {
        this.wrapupTime = wrapupTime;
        if (wrapupTime != null) {
            putBodyParameter("wrapupTime", wrapupTime);
        }
    }

    public Integer getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(Integer serviceLevel) {
        this.serviceLevel = serviceLevel;
        if (serviceLevel != null) {
            putBodyParameter("serviceLevel", serviceLevel);
        }
    }

    public Integer getJoinEmpty() {
        return joinEmpty;
    }

    public void setJoinEmpty(Integer joinEmpty) {
        this.joinEmpty = joinEmpty;
        if (joinEmpty != null) {
            putBodyParameter("joinEmpty", joinEmpty);
        }
    }

    public List<QueueMemberModel> getQueueMembers() {
        return queueMembers;
    }

    public void setQueueMembers(List<QueueMemberModel> queueMembers) {
        this.queueMembers = queueMembers;
        if (queueMembers != null) {
            putBodyParameter("queueMembers", queueMembers);
        }
    }

    public UpdateQueueRequest() {
        super(PathEnum.UpdateQueue.value(), HttpMethodType.POST);
    }

    @Override
    public Class<UpdateQueueResponse> getResponseClass() {
        return UpdateQueueResponse.class;
    }

    public Integer getChatStrategy() {
        return chatStrategy;
    }

    public void setChatStrategy(Integer chatStrategy) {
        this.chatStrategy = chatStrategy;
        if (chatStrategy != null) {
            putBodyParameter("chatStrategy", chatStrategy);
        }
    }

    public Integer getChatMaxWait() {
        return chatMaxWait;
    }

    public void setChatMaxWait(Integer chatMaxWait) {
        this.chatMaxWait = chatMaxWait;
        if (chatMaxWait != null) {
            putBodyParameter("chatMaxWait", chatMaxWait);
        }
    }

    public Integer getChatLocation() {
        return chatLocation;
    }

    public void setChatLocation(Integer chatLocation) {
        this.chatLocation = chatLocation;
        if (chatLocation != null) {
            putBodyParameter("chatLocation", chatLocation);
        }
    }

    public Integer getSayCno() {
        return sayCno;
    }

    public void setSayCno(Integer sayCno) {
        this.sayCno = sayCno;
        if (sayCno != null) {
            putBodyParameter("sayCno", sayCno);
        }
    }

    public Integer getVipSupport() {
        return vipSupport;
    }

    public void setVipSupport(Integer vipSupport) {
        this.vipSupport = vipSupport;
        if (vipSupport != null) {
            putBodyParameter("vipSupport", vipSupport);
        }
    }
}
