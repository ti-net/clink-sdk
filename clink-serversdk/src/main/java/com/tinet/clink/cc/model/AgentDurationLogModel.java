package com.tinet.clink.cc.model;

/**
 * @author wangli
 * @date 2022-03-10 5:31 PM
 */
public class AgentDurationLogModel {

    /**
     * 座席工号
     */
    private String cno;

    /**
     * 座席姓名
     */
    private String agentName;

    /**
     * 所属队列
     */
    private String qname;

    /**
     * 开始时间
     */
    private Integer startTime;

    /**
     * 结束时间
     */
    private Integer endTime;

    /**
     * 持续时间
     */
    private Integer duration;

    /**
     * 状态描述
     */
    private String stateDesc;

    /**
     * 创建时间
     */
    private Integer createTime;

    /**
     * 附加数据1
     */
    private String data1;

    /**
     * 附加数据2
     */
    private String data2;

    /**
     * 描述信息
     */
    private String detail;

    /**
     * 统计类型 2:呼叫中心  3：在线客服
     */
    private Integer statisticType;

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getStatisticType() {
        return statisticType;
    }

    public void setStatisticType(Integer statisticType) {
        this.statisticType = statisticType;
    }
}
