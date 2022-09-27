package com.tinet.clink.openapi.response.kb;

import com.tinet.clink.openapi.model.KbDirectoryResponseModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 知识库目录列表响应
 *
 * @author feizq
 * @date 2021/06/25
 **/
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ListDirectoriesResponse extends PagedResponse {

    private List<KbDirectoryResponseModel> directories;

    public List<KbDirectoryResponseModel> getDirectories() {
        return directories;
    }

    public void setDirectories(List<KbDirectoryResponseModel> directories) {
        this.directories = directories;
    }
}
