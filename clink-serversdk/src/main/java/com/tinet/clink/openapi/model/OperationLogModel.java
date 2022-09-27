package com.tinet.clink.openapi.model;

/**
 * 座席操作日志返回对象
 *
 * @author wangli
 * @date 2022-03-09 5:12 PM
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  OperationLogModel {

    /**
     * 企业id
     */
    private String enterpriseId;

    /**
     * 操作平台
     */
    private String platform;

    /**
     * 座席编号
     */
    private String cno;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作模块
     */
    private String module;

    /**
     * 操作对象
     */
    private String object;

    /**
     * 操作方式
     */
    private String optType;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 操作时间
     */
    private Long operateTime;

    /**
     * 操作结果
     */
    private String result;

    /**
     * 详细信息
     */
    private String comment;

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Long getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Long operateTime) {
        this.operateTime = operateTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
