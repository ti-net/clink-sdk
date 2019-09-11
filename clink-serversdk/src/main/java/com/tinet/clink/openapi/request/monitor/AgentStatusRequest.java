package com.tinet.clink.openapi.request.monitor;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.monitor.AgentStatusResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.List;

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
    private List<String> agentStatus;
    /**
     * 置忙类型
     */
    private List<Integer> pauseTypes;
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
            putQueryParameter("qnos", qnos);
        }

    }

    public String[] getCnos() {
        return cnos;
    }

    public void setCnos(String[] cnos) {
        this.cnos = cnos;
        if (cnos != null) {
            putQueryParameter("cnos", cnos);
        }
    }

    public List<String> getAgentStatus() {
        return agentStatus;
    }

    public void setAgentStatus(List<String> agentStatus) {
        this.agentStatus = agentStatus;
        if (agentStatus != null) {
            putQueryParameter("agentStatus", agentStatus);
        }
    }
    
    public List<Integer> getPauseTypes() {
        return pauseTypes;
    }

    public void setPauseTypes(List<Integer> pauseTypes) {
        this.pauseTypes = pauseTypes;
        if (pauseTypes != null) {
            putQueryParameter("pauseTypes", pauseTypes);
        }
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
