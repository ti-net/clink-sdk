package com.tinet.clink.kb.response;

import com.tinet.clink.kb.model.KbDirectoryResponseModel;
import com.tinet.clink.core.response.PagedResponse;

import java.util.List;

/**
 * 知识库目录列表响应
 *
 * @author feizq
 * @date 2021/06/25
 **/
public class ListDirectoriesResponse extends PagedResponse {

    private List<KbDirectoryResponseModel> directories;

    public List<KbDirectoryResponseModel> getDirectories() {
        return directories;
    }

    public void setDirectories(List<KbDirectoryResponseModel> directories) {
        this.directories = directories;
    }
}
