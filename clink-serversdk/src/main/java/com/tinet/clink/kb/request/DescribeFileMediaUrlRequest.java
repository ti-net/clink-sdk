package com.tinet.clink.kb.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.kb.PathEnum;
import com.tinet.clink.kb.response.DescribeFileMediaUrlResponse;
import com.tinet.clink.kb.response.SearchKnowledgeOnOpenResponse;

/**
 * @author zhangpc
 * @since 2024/06/05
 */
public class DescribeFileMediaUrlRequest extends AbstractRequestModel<DescribeFileMediaUrlResponse> {

    private Integer fileId;

    private Boolean inline;


    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
        if (fileId != null) {
            putQueryParameter("fileId", fileId);
        }
    }

    public Boolean getInline() {
        return inline;
    }

    public void setInline(Boolean inline) {
        this.inline = inline;
        if (inline != null) {
            putQueryParameter("inline", inline);
        }

    }

    public DescribeFileMediaUrlRequest() {
        super(PathEnum.DescribeFileMediaUrl.value(), HttpMethodType.GET);
    }

    @Override
    public Class<DescribeFileMediaUrlResponse> getResponseClass() {
        return DescribeFileMediaUrlResponse.class;
    }
}
