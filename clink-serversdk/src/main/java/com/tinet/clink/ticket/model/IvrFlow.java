package com.tinet.clink.ticket.model;

/**
 * IvrFlow返回对象
 *
 * @author huwk
 * @date 2018/09/11
 **/
public class IvrFlow {

    /**
     * 路径名
     */
    private String pathName;

    /**
     * 路径
     */
    private String path;

    /**
     * 路由开始时间
     */
    private Long routerStartTime;

    /**
     * 路由结束时间
     */
    private Long routerEndTime;

    /**
     * 路由名
     */
    private String routerName;

    /**
     * ivr名
     */
    private String ivrName;

    /**
     * 执行动作
     */
    private String action;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 按键值
     */
    private String pressKey;

    /**
     * 按键时间
     */
    private Long pressTime;

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getRouterStartTime() {
        return routerStartTime;
    }

    public void setRouterStartTime(Long routerStartTime) {
        this.routerStartTime = routerStartTime;
    }

    public Long getRouterEndTime() {
        return routerEndTime;
    }

    public void setRouterEndTime(Long routerEndTime) {
        this.routerEndTime = routerEndTime;
    }

    public String getRouterName() {
        return routerName;
    }

    public void setRouterName(String routerName) {
        this.routerName = routerName;
    }

    public String getIvrName() {
        return ivrName;
    }

    public void setIvrName(String ivrName) {
        this.ivrName = ivrName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }


    public String getPressKey() {
        return pressKey;
    }

    public void setPressKey(String pressKey) {
        this.pressKey = pressKey;
    }

    public Long getPressTime() {
        return pressTime;
    }

    public void setPressTime(Long pressTime) {
        this.pressTime = pressTime;
    }
}
