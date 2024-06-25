package com.tinet.clink.aikb.request;

import com.tinet.clink.aikb.KnowledgeType;
import com.tinet.clink.aikb.PathEnum;
import com.tinet.clink.aikb.response.DirectoryCreateResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

/**
 * @author houwd
 * @since 2024/06/25
 */
public class DirectoryCreateRequest extends AbstractRequestModel<DirectoryCreateResponse> {

    /**
     * 知识库id
     */
    private Integer repositoryId;

    /**
     * 名称
     */
    private String name;

    /**
     * 所属目录类型
     */
    private KnowledgeType type;

    /**
     * 父目录ID
     */
    private Integer parentId;

    public Integer getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(Integer repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KnowledgeType getType() {
        return type;
    }

    public void setType(KnowledgeType type) {
        this.type = type;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public DirectoryCreateRequest() {
        super(PathEnum.CreateDirectory.value(), HttpMethodType.POST);
    }

    @Override
    public Class<DirectoryCreateResponse> getResponseClass() {
        return DirectoryCreateResponse.class;
    }
}
