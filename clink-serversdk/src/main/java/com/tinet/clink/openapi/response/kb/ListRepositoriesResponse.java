package com.tinet.clink.openapi.response.kb;

import com.tinet.clink.openapi.model.KbRepositoryResponseModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 知识库仓库列表响应
 *
 * @author feizq
 * @date 2021/06/25
 **/
public class ListRepositoriesResponse extends PagedResponse {

    private List<KbRepositoryResponseModel> repositories;

    public List<KbRepositoryResponseModel> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<KbRepositoryResponseModel> repositories) {
        this.repositories = repositories;
    }
}
