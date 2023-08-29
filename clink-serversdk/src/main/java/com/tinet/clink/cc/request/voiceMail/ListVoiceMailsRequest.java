package com.tinet.clink.cc.request.voiceMail;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.voiceMail.ListVoiceMailsResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;

import java.util.Objects;

/**
 * Class For:
 *
 * @author Tinet-yinzk
 * @date 2023/8/29 14:16
 */
public class ListVoiceMailsRequest extends AbstractRequestModel<ListVoiceMailsResponse> {

    /**
     * 偏移量，范围 0-99990。默认值为 0，但limit + offset 不允许超过100000
     */
    private Integer offset;

    /**
     * 查询条数，范围 10-100。默认值为 10，但limit + offset 不允许超过100000
     */
    private Integer limit;


    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (Objects.nonNull(limit)) {
            this.putQueryParameter("limit", limit);
        }
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
        if (Objects.nonNull(offset)) {
            this.putQueryParameter("offset", offset);
        }
    }

    public ListVoiceMailsRequest(String path) {
        super(PathEnum.ListVoiceMails.value(), HttpMethodType.GET);
    }

    @Override
    public Class<ListVoiceMailsResponse> getResponseClass() {
        return ListVoiceMailsResponse.class;
    }
}
