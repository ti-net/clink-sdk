package com.tinet.clink.livechat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

/**
 * 知识库目录
 *
 * @author feizq
 * @date 2021/06/25
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class KbDirectory {

    /**
     * 目录ID
     */
    private Integer id;

    /**
     * 企业ID
     */
    private String enterpriseId;

    /**
     * 目录名称
     */
    private String name;


    /**
     * 知识库ID
     */
    private Integer kbId;

    /**
     * 父节点ID
     */
    private Integer parentId;

    /**
     * 树物理路径
     */
    private String path;

    /**
     * 是否叶子节点 0 非叶子 1 叶子
     */
    private Integer leaf;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 平台登录名
     */
    private String accountLoginName;
    /**
     * 属于哪一种类型的知识库
     */
    private Integer type;
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

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKbId() {
        return kbId;
    }

    public void setKbId(Integer kbId) {
        this.kbId = kbId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getLeaf() {
        return leaf;
    }

    public void setLeaf(Integer leaf) {
        this.leaf = leaf;
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

    public String getAccountLoginName() {
        return accountLoginName;
    }

    public void setAccountLoginName(String accountLoginName) {
        this.accountLoginName = accountLoginName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<KbDirectory> getSubDirectories() {
        return subDirectories;
    }

    public void setSubDirectories(List<KbDirectory> subDirectories) {
        this.subDirectories = subDirectories;
    }
}
