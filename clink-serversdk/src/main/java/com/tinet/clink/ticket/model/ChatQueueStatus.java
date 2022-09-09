package com.tinet.clink.ticket.model;

import java.util.List;

/**
 * 在线客服 队列状态对象
 * @author dengsx
 * @create 2022/01/17
 **/
public class ChatQueueStatus {

    /**
     * 队列号
     */
    private String qno;

    /**
     * 队列名
     */
    private String name;

    /**
     * 空闲座席数
     */
    private int idle;

    /**
     * 登录座席数
     */
    private int login;

    /**
     * 座席总数（登录 + 未登录）
     */
    private int total;

    /**
     * 队列里排队的数量
     */
    private Long queuedCount;

    /**
     * 座席列表
     */
    private List<ChatAgentStatusModel> clients;


    public String getQno() {
        return qno;
    }

    public void setQno(String qno) {
        this.qno = qno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdle() {
        return idle;
    }

    public void setIdle(int idle) {
        this.idle = idle;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Long getQueuedCount() {
        return queuedCount;
    }

    public void setQueuedCount(Long queuedCount) {
        this.queuedCount = queuedCount;
    }

    public List<ChatAgentStatusModel> getClients() {
        return clients;
    }

    public void setClients(List<ChatAgentStatusModel> clients) {
        this.clients = clients;
    }

}
