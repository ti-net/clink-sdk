package com.tinet.smartlink.openapi.model.sqc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Collection;
import java.util.Set;

/**
 * Class for:
 * 客户画像
 *
 * @author yinzk
 * @date 2020/8/17
 */
@Data
public class CustomerPortrait {

    /**
     * 记录id
     */
    private String id;
    /**
     * 客户号码
     */
    private String customerNumber;
    /**
     * 客户归属地
     */
    private String customerCity;
    /**
     * excel导出结果：最近一次通话时间
     */
    @JsonIgnore
    private String excelRecentlyCallTime;
    /**
     * 总通话数
     */
    private Integer totalCallCount;
    /**
     * 最长呼叫时长 （单位：s）
     */
    private Integer maxCallDuration;
    /**
     * 最大对话轮数
     */
    private Integer maxRound;
    /**
     * 平均对话轮数
     */
    private Double avgRound;
    /**
     * 平均通话时长
     */
    private Double avgCallDuration;
    /**
     * 账号名
     */
    private String accountLoginName;
    /**
     * 话单记录id集合
     */
    private Set<String> cdrIds;
    /**
     * 总通话时长 (单位:s)
     */
    private Integer totalCallDuration;
    /**
     * 总对话轮数
     */
    private Integer totalRound;
    /**
     * 最近一次通话时间(时间戳，单位:s)
     */
    private Long recentlyCallTime;
    /**
     * 画像细节(打标签细节)
     */
    private Collection<PortraitDetail> portraitDetails;
}

