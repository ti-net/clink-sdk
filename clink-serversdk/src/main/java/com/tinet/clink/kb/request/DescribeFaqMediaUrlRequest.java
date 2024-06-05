package com.tinet.clink.kb.request;

import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.kb.PathEnum;
import com.tinet.clink.kb.response.DescribeFaqMediaUrlResponse;
import com.tinet.clink.kb.response.DescribeFileMediaUrlResponse;

/**
 * @author zhangpc
 * @since 2024/06/05
 */
public class DescribeFaqMediaUrlRequest extends AbstractRequestModel<DescribeFaqMediaUrlResponse> {

    private Integer faqId;

    private String fileKey;

    private Boolean inline;

    public Integer getFaqId() {
        return faqId;
    }

    public void setFaqId(Integer faqId) {
        this.faqId = faqId;
        if (faqId != null) {
            putQueryParameter("faqId", faqId);
        }
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
        if (fileKey != null) {
            putQueryParameter("fileKey", fileKey);
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

    public DescribeFaqMediaUrlRequest() {
        super(PathEnum.DescribeFaqMediaUrl.value(), HttpMethodType.GET);
    }

    @Override
    public Class<DescribeFaqMediaUrlResponse> getResponseClass() {
        return DescribeFaqMediaUrlResponse.class;
    }
}
