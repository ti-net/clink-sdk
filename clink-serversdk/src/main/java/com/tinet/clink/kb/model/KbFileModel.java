package com.tinet.clink.kb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 文件库实体
 *
 * @author huwk
 * @date 2019/10/11
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class KbFileModel{


    /**
     *
     */
    private String id;

    /**
     * 企业ID
     */
    private String enterpriseId;

    /**
     * 标题
     */
    private String title;

    /**
     * 文件大小(K)
     */
    private Long fileSize;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件内容
     */
    private String content;
    /**
     * 搜索出的文档片段
     */
    private List<String> contentList;

    /**
     * 目录ID
     */
    private Integer directoryId;

    /**
     * 知识库ID
     */
    private Integer kbId;

    /**
     * 所属知识库类型，冗余kbId对应库的类型
     */
    private Integer kbType;

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
     * 0是非置顶 ，1是置顶 默认是0
     */
    private Integer topStatus;

    /**
     * 文件点赞数量
     */
    private Integer likeCount;

    /**
     * 文件点踩数量
     */
    private Integer dislikeCount;

    /**
     * 0 已点赞 1 已点踩  null 未被点击
     */
    private String status;

    /**
     * 文件热点数量
     */
    private Integer heat;

    /**
     * 文件说明
     */
    private String description;


    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getContentList() {
        return contentList;
    }

    public void setContentList(List<String> contentList) {
        this.contentList = contentList;
    }

    public Integer getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(Integer directoryId) {
        this.directoryId = directoryId;
    }

    public Integer getKbId() {
        return kbId;
    }

    public void setKbId(Integer kbId) {
        this.kbId = kbId;
    }

    public Integer getKbType() {
        return kbType;
    }

    public void setKbType(Integer kbType) {
        this.kbType = kbType;
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

    public Integer getTopStatus() {
        return topStatus;
    }

    public void setTopStatus(Integer topStatus) {
        this.topStatus = topStatus;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(Integer dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getHeat() {
        return heat;
    }

    public void setHeat(Integer heat) {
        this.heat = heat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
