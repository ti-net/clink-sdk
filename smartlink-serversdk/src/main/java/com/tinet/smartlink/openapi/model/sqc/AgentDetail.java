package com.tinet.smartlink.openapi.model.sqc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 王大宝
 * @date 2019/5/14
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentDetail {

    /**
     * 用户ID
     */
    @JsonProperty("enterpriseId")
    private String userId;

    /**
     * 座席工号
     */
    @JsonProperty("cno")
    private String agentNumber;

    /**
     * 座席姓名
     */
    @JsonProperty("name")
    private String agentName;

    /**
     * 工龄
     */
    @JsonProperty("workingAge")
    private String workingAge;

    /**
     * 转写优先级
     */
    @JsonProperty("description")
    private String description;
}
