package com.tinet.clink.openapi.model;

/**
 * 排队对象
 *
 * @author wangll
 * @date 2019-11-18
 */
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  QueueEntryModel {

    /**
     * 排队客户主叫号码
     */
    private String customerNumber;
    /**
     * 排队客户来电时间
     */
    private Integer startTime;
    /**
     * 排队客户进入时间
     */
    private Integer joinTime;
    /**
     * 排队客户等待时间
     */
    private Long waitTime;
    /**
     * 排队客户VIP级别
     */
    private Integer priority;
    /**
     * 通话唯一标识
     */
    private String uniqueId;
    /**
     * 溢出次数
     */
    private Integer overflow;
    /**
     * 排队位置
     */
    private Integer position;

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Integer joinTime) {
        this.joinTime = joinTime;
    }

    public Long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Long waitTime) {
        this.waitTime = waitTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Integer getOverflow() {
        return overflow;
    }

    public void setOverflow(Integer overflow) {
        this.overflow = overflow;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
