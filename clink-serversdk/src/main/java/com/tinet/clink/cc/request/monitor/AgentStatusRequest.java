package com.tinet.clink.cc.request.monitor;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.monitor.AgentStatusResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


/**
 * 查询座席状态请求对象
 *
 * @author wangll
 * @date 2019/09/11
 **/
public class AgentStatusRequest extends AbstractRequestModel<AgentStatusResponse> {

    /**
     * 队列号数组
     */
    private String[] qnos;
    /**
     * 座席号数组
     */
    private String[] cnos;
    /**
     * 座席状态
     */
    private String[] agentStatus;

    /**
     * 座席状态详情
     */
    private String[] agentStatusDetails;

    /**
     * 置忙类型
     */
    private Integer[] pauseTypes;
    /**
     * 偏移量，默认值为 0，最大范围 10000
     */
    private Integer offset;

    /**
     * 查询记录条数，默认值为 10，最大范围 100
     */
    private Integer limit;


    public AgentStatusRequest() {
        super(PathEnum.AgentStatus.value(), HttpMethodType.GET);
    }

    @Override
    public Class<AgentStatusResponse> getResponseClass() {
        return AgentStatusResponse.class;
    }

    public String[] getQnos() {
        return qnos;
    }

    public void setQnos(String[] qnos) {
        this.qnos = qnos;
        if (qnos != null) {
            for (int i = 0; i < qnos.length; i++) {
                putQueryParameter("qnos[" + i + "]", qnos[i]);
            }
        }
    }

    public String[] getCnos() {
        return cnos;
    }

    public void setCnos(String[] cnos) {
        this.cnos = cnos;
        if (cnos != null) {
            for (int i = 0; i < cnos.length; i++) {
                putQueryParameter("cnos[" + i + "]", cnos[i]);
            }
        }
    }

    public void setAgentStatus(String[] agentStatus) {
        this.agentStatus = agentStatus;
        if (agentStatus != null) {
            for (int i = 0; i < agentStatus.length; i++) {
                putQueryParameter("agentStatus[" + i + "]", agentStatus[i]);
            }
        }
    }

    public void setAgentStatusDetails(String[] agentStatusDetails) {
        this.agentStatusDetails = agentStatusDetails;
        if (agentStatusDetails != null) {
            for (int i = 0; i < agentStatusDetails.length; i++) {
                putQueryParameter("agentStatusDetails[" + i + "]", agentStatusDetails[i]);
            }
        }
    }

    public void setPauseTypes(Integer[] pauseTypes) {
        this.pauseTypes = pauseTypes;
        if (pauseTypes != null) {
            for (int i = 0; i < pauseTypes.length; i++) {
                putQueryParameter("pauseTypes[" + i + "]", pauseTypes[i]);
            }
        }
    }

    public String[] getAgentStatus() {
        return agentStatus;
    }

    public String[] getAgentStatusDetails() {
        return agentStatusDetails;
    }

    public Integer[] getPauseTypes() {
        return pauseTypes;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (offset != null) {
            putQueryParameter("offset", offset);
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (limit != null) {
            putQueryParameter("limit", limit);
        }
    }
}
