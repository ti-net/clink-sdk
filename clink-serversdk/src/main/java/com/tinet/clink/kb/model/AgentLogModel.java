package com.tinet.clink.kb.model;

/**
 * @author wangli
 * @date 2022-03-09 6:11 PM
 */
public class AgentLogModel {

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
     * 事件描述
     */
    private String eventDesc;

    /**
     * 事件时间
     */
    private Integer eventTime;

    /**
     * 附加数据1
     */
    private String data1;

    /**
     * 附加数据2
     */
    private String data2;

    /**
     * 附加数据3
     */
    private String data3;

    /**
     * 附加数据4
     */
    private String data4;

    /**
     * 附加数据5
     */
    private String data5;

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

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public Integer getEventTime() {
        return eventTime;
    }

    public void setEventTime(Integer eventTime) {
        this.eventTime = eventTime;
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

    public String getData3() {
        return data3;
    }

    public void setData3(String data3) {
        this.data3 = data3;
    }

    public String getData4() {
        return data4;
    }

    public void setData4(String data4) {
        this.data4 = data4;
    }

    public String getData5() {
        return data5;
    }

    public void setData5(String data5) {
        this.data5 = data5;
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
