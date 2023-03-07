package com.tinet.clink.kb.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.kb.PathEnum;
import com.tinet.clink.kb.response.DeleteFileResponse;
import com.tinet.clink.kb.response.ListFileResponse;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author 周先康
 * @create 2023/3/7 10:48
 */
public class DeleteFileRequest extends AbstractRequestModel<DeleteFileResponse> {

    private String[] ids;

    private String[] filePaths;

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
        if (ids != null){
            putBodyParameter("ids",ids);
        }
    }

    public String[] getFilePaths() {
        return filePaths;
    }

    public void setFilePaths(String[] filePaths) {
        this.filePaths = filePaths;
        if (filePaths != null){
            putBodyParameter("filePaths",filePaths);
        }
    }

    public DeleteFileRequest() {
        super(PathEnum.DeleteFile.value(), HttpMethodType.POST);
    }



    /**
     * 获取响应对象
     *
     * @return 响应对象
     */
    @Override
    public Class<DeleteFileResponse> getResponseClass() {
        return DeleteFileResponse.class;
    }
}
