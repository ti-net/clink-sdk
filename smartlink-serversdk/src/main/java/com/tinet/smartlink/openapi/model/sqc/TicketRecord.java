package com.tinet.smartlink.openapi.model.sqc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * ClassFor:
 * 工单流转节点
 *
 * @author yinzk
 * @date 2019/08/26
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketRecord {

    /**
     * 座席名称
     */
    private String agentName;
    /**
     * 座席工号
     */
    private String cno;
    /**
     * 工单节点创建时间
     */
    private Long createTime;
    /**
     * 部门id
     */
    private String enterpriseId;
    /**
     * 节点类型:1:开始节点,0:过程节点,-1:完结节点
     */
    private Integer nodeType;
    /**
     * 队列名
     */
    private String qname;
    /**
     * 队列号
     */
    private String qno;
    /**
     * 工单流转节点id
     */
    private String nodeId;
    /**
     * 工单节点
     */
    private String text;
    /**
     * 更新时间
     */
    private Long updateTime;

}
