package com.tinet.clink.crm.model;

import java.util.Date;

/**
 * 添加客户资料数据模型
 *
 * @author gexd
 * @date 2023/03/08
 */
public class QueryCustomerModel {

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 企业id
     */
    private Integer enterpriseId;

    /**
     * 客户姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 电话
     */
    private String[] tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 客户等级，0：普通、1：VIP
     */
    private String level;

    /**
     * 归属类型，0：全体共享、1：队列共享、2：座席私有、3：无归属
     */
    private String shareType;

    /**
     * 归属
     */
    private String share;

    /**
     * 备注
     */
    private String remark;

    /**
     * 来源，0：呼叫中心 、1：在线咨询、2：微信、3：人工添加 、4：邮件
     */
    private String source;

    /**
     * 创建人类型，0：座席、1：管理员
     */
    private String creatorType;

    /**
     * 创建人
     */
    private Integer creatorId;

    /**
     * 修改人类型，0：座席、1：管理员
     */
    private String modifierType;

    /**
     * 修改人，新增时创建人即修改人
     */
    private Integer modifierId;

    /**
     * 最后一次联系时间
     */
    private Date lastContactTime;

    /**
     * 最后一次联系类型，1：呼入，4：外呼
     */
    private Integer lastContactType;

    /**
     * 删除标识，0：未删除、1：已删除
     */
    private Integer delete;

    /**
     * 自定义字段
     */
    private CustomizeField[] customize;

    /**
     * 记录创建时间
     */
    private Date createTime;

    /**
     * 记录修改时间
     */
    private Date updateTime;

    /**
     * 访客标识
     */
    private String[] visitorIds;

    /**
     * 创建人姓名
     */
    private String creatorName;

    /**
     * 修改人姓名
     */
    private String modifierName;

    /**
     * 呼入接通次数
     */
    private Integer ibBridgedNumber;

    /**
     * 呼出接通次数
     */
    private Integer obBridgedNumber;

    /**
     * 呼出次数
     */
    private Integer obNumber;

    /**
     * 分配时间
     */
    private Date assignTime;

    /**
     * 外部id（第三方平台的id）
     */
    private String externalId;

    /**
     * 呼入次数
     */
    private Integer ibNumber;

    /**
     * 是否为回收客户
     */
    private Integer retrieve;

    /**
     * 回收时间
     */
    private Date retrieveTime;

    /**
     * 无归属授权队列
     */
    private Integer[] queueWithoutAttribution;

    /**
     * 客户阶段id
     * */
    private Integer phaseId;

    /**
     * 客户阶段id
     * */
    private Integer phaseReasonId;

    /**
     * 推广来源
     * */
    private String promoteSource;

    /**
     * 重复推广次数
     * */
    private Integer repeatPromoteCount;

    /**
     * 最近一次重复推广时间
     * */
    private Date lastRepeatPromoteTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String[] getTel() {
        return tel;
    }

    public void setTel(String[] tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreatorType() {
        return creatorType;
    }

    public void setCreatorType(String creatorType) {
        this.creatorType = creatorType;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getModifierType() {
        return modifierType;
    }

    public void setModifierType(String modifierType) {
        this.modifierType = modifierType;
    }

    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    public Date getLastContactTime() {
        return lastContactTime;
    }

    public void setLastContactTime(Date lastContactTime) {
        this.lastContactTime = lastContactTime;
    }

    public Integer getLastContactType() {
        return lastContactType;
    }

    public void setLastContactType(Integer lastContactType) {
        this.lastContactType = lastContactType;
    }

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
    }

    public CustomizeField[] getCustomize() {
        return customize;
    }

    public void setCustomize(CustomizeField[] customize) {
        this.customize = customize;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String[] getVisitorIds() {
        return visitorIds;
    }

    public void setVisitorIds(String[] visitorIds) {
        this.visitorIds = visitorIds;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public Integer getIbBridgedNumber() {
        return ibBridgedNumber;
    }

    public void setIbBridgedNumber(Integer ibBridgedNumber) {
        this.ibBridgedNumber = ibBridgedNumber;
    }

    public Integer getObBridgedNumber() {
        return obBridgedNumber;
    }

    public void setObBridgedNumber(Integer obBridgedNumber) {
        this.obBridgedNumber = obBridgedNumber;
    }

    public Integer getObNumber() {
        return obNumber;
    }

    public void setObNumber(Integer obNumber) {
        this.obNumber = obNumber;
    }

    public Date getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(Date assignTime) {
        this.assignTime = assignTime;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Integer getIbNumber() {
        return ibNumber;
    }

    public void setIbNumber(Integer ibNumber) {
        this.ibNumber = ibNumber;
    }

    public Integer getRetrieve() {
        return retrieve;
    }

    public void setRetrieve(Integer retrieve) {
        this.retrieve = retrieve;
    }

    public Date getRetrieveTime() {
        return retrieveTime;
    }

    public void setRetrieveTime(Date retrieveTime) {
        this.retrieveTime = retrieveTime;
    }

    public Integer[] getQueueWithoutAttribution() {
        return queueWithoutAttribution;
    }

    public void setQueueWithoutAttribution(Integer[] queueWithoutAttribution) {
        this.queueWithoutAttribution = queueWithoutAttribution;
    }

    public Integer getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(Integer phaseId) {
        this.phaseId = phaseId;
    }

    public Integer getPhaseReasonId() {
        return phaseReasonId;
    }

    public void setPhaseReasonId(Integer phaseReasonId) {
        this.phaseReasonId = phaseReasonId;
    }

    public String getPromoteSource() {
        return promoteSource;
    }

    public void setPromoteSource(String promoteSource) {
        this.promoteSource = promoteSource;
    }

    public Integer getRepeatPromoteCount() {
        return repeatPromoteCount;
    }

    public void setRepeatPromoteCount(Integer repeatPromoteCount) {
        this.repeatPromoteCount = repeatPromoteCount;
    }

    public Date getLastRepeatPromoteTime() {
        return lastRepeatPromoteTime;
    }

    public void setLastRepeatPromoteTime(Date lastRepeatPromoteTime) {
        this.lastRepeatPromoteTime = lastRepeatPromoteTime;
    }
}
