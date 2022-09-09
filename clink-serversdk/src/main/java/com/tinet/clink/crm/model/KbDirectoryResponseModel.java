package com.tinet.clink.crm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 知识库仓库响应参数
 *
 * @author feizq
 * @date 2021/06/25
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class KbDirectoryResponseModel {

    private Integer id;
    /**
     * 知识库名称
     */
    private String name;

    /**
     * 知识库类型
     */
    private Integer type;

    /**
     * 是否绑定机器人
     */
    private Boolean hasBundleBot;


    /**
     * 企业ID，在机器人中对应的userId
     */
    private String enterpriseId;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 平台登录名
     */
    private String accountLoginName;
    /**
     * 知识库仓库列表排名位置
     */
    private Integer ranking;
    /**
     * 知识库下的目录路径
     */
    private List<KbDirectory> subDirectories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getHasBundleBot() {
        return hasBundleBot;
    }

    public void setHasBundleBot(Boolean hasBundleBot) {
        this.hasBundleBot = hasBundleBot;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getAccountLoginName() {
        return accountLoginName;
    }

    public void setAccountLoginName(String accountLoginName) {
        this.accountLoginName = accountLoginName;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public List<KbDirectory> getSubDirectories() {
        return subDirectories;
    }

    public void setSubDirectories(List<KbDirectory> subDirectories) {
        this.subDirectories = subDirectories;
    }
}
