package com.tinet.clink.openapi.model;


import java.util.List;
import java.util.StringJoiner;

/**
 * 座席详情对象
 *
 * @author lizy
 * @date 2018/09/12
 */
public class ClientDetailModel {

    /**
     * 座席号
     */
    private String cno;

    /**
     * 座席名称
     */
    private String name;

    /**
     * 区号
     */
    private String areaCode;


    /**
     * 绑定电话
     */
    private String bindTel;

    /**
     * 绑定电话类型
     */
    private Integer telType;

    /**
     * 座席角色，0普通座席 1班长座席。
     */
    private Integer role;

    /**
     * 启用状态 0停用 1启用。
     */
    private Integer active;

    /**
     * 座席状态 0离线 1离线
     */
    private Integer status;

    /**
     * 号码隐藏类型，0不隐藏 1全局。
     */
    private Integer hiddenTel;

    /**
     * 座席权限
     */
    private ClientPermission permission;

    /**
     * 队列号
     */
    private List<String> qnos;

    /**
     * 外显号
     */
    private List<String> clid;

    /**
     * 外显类型
     */
    private Integer clidType;


    /**
     * 外显号配置
     */
    private List<ClidArea> clidArea;

    /**
     * 外显规则
     */
    private Integer clidRule;

    /**
     * 轮选类型
     */
    private Integer recurrentselectionType;

    /**
     * 轮选值
     */
    private Integer recurrentselectionValue;

    /**
     * 座席类型，1：全渠道、2：呼叫中心、3：在线客服
     */
    private Integer type;

    /**
     * 在线客服座席会话上限开关，0：关闭、1：开启
     */
    private Integer chatLimit;

    /**
     * 在线客服座席会话上限
     */
    private Integer chatLimitNum;

    /**
     * 队列信息
     */
    private List<QueueResultModel> queues;


    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getBindTel() {
        return bindTel;
    }

    public void setBindTel(String bindTel) {
        this.bindTel = bindTel;
    }

    public Integer getTelType() {
        return telType;
    }

    public void setTelType(Integer telType) {
        this.telType = telType;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getHiddenTel() {
        return hiddenTel;
    }

    public void setHiddenTel(Integer hiddenTel) {
        this.hiddenTel = hiddenTel;
    }

    public ClientPermission getPermission() {
        return permission;
    }

    public void setPermission(ClientPermission permission) {
        this.permission = permission;
    }

    public List<String> getQnos() {
        return qnos;
    }

    public void setQnos(List<String> qnos) {
        this.qnos = qnos;
    }

    public List<String> getClid() {
        return clid;
    }

    public void setClid(List<String> clid) {
        this.clid = clid;
    }

    public Integer getClidType() {
        return clidType;
    }

    public void setClidType(Integer clidType) {
        this.clidType = clidType;
    }

    public List<ClidArea> getClidArea() {
        return clidArea;
    }

    public void setClidArea(List<ClidArea> clidArea) {
        this.clidArea = clidArea;
    }

    public Integer getClidRule() {
        return clidRule;
    }

    public void setClidRule(Integer clidRule) {
        this.clidRule = clidRule;
    }

    public Integer getRecurrentselectionType() {
        return recurrentselectionType;
    }

    public void setRecurrentselectionType(Integer recurrentselectionType) {
        this.recurrentselectionType = recurrentselectionType;
    }

    public Integer getRecurrentselectionValue() {
        return recurrentselectionValue;
    }

    public void setRecurrentselectionValue(Integer recurrentselectionValue) {
        this.recurrentselectionValue = recurrentselectionValue;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getChatLimit() {
        return chatLimit;
    }

    public void setChatLimit(Integer chatLimit) {
        this.chatLimit = chatLimit;
    }

    public Integer getChatLimitNum() {
        return chatLimitNum;
    }

    public void setChatLimitNum(Integer chatLimitNum) {
        this.chatLimitNum = chatLimitNum;
    }

    public List<QueueResultModel> getQueues() {
        return queues;
    }

    public void setQueues(List<QueueResultModel> queues) {
        this.queues = queues;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ClientDetailModel.class.getSimpleName() + "[", "]")
                .add("cno='" + cno + "'")
                .add("name='" + name + "'")
                .add("areaCode='" + areaCode + "'")
                .add("bindTel='" + bindTel + "'")
                .add("telType=" + telType)
                .add("role=" + role)
                .add("active=" + active)
                .add("status=" + status)
                .add("hiddenTel=" + hiddenTel)
                .add("permission=" + permission)
                .add("qnos=" + qnos)
                .add("clid=" + clid)
                .add("clidType=" + clidType)
                .add("clidArea=" + clidArea)
                .add("clidRule=" + clidRule)
                .add("recurrentselectionType=" + recurrentselectionType)
                .add("recurrentselectionValue=" + recurrentselectionValue)
                .add("type=" + type)
                .add("chatLimit=" + chatLimit)
                .add("chatLimitNum=" + chatLimitNum)
                .add("queues=" + queues)
                .toString();
    }
}
