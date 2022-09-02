package com.tinet.clink.cc.response.cdr;

import com.tinet.clink.openapi.model.CdrCommentModel;
import com.tinet.clink.openapi.response.PagedResponse;

import java.util.List;

/**
 * 查询留言记录列表响应
 *
 * @author huwk
 * @date 2018/10/24
 **/
public class ListCommentsResponse extends PagedResponse {

    /**
     * 留言记录列表
     */
    private List<CdrCommentModel> cdrComments;

    public List<CdrCommentModel> getCdrComments() {
        return cdrComments;
    }

    public void setCdrComments(List<CdrCommentModel> cdrComments) {
        this.cdrComments = cdrComments;
    }
}
